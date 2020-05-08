package io.openems.edge.controller.battery.batteryprotection.state;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.openems.common.exceptions.OpenemsError.OpenemsNamedException;
import io.openems.edge.battery.api.Battery;
import io.openems.edge.controller.battery.batteryprotection.IState;
import io.openems.edge.controller.battery.batteryprotection.State;
import io.openems.edge.ess.api.ManagedSymmetricEss;

public class ForceCharge extends BaseState implements IState {

	private final Logger log = LoggerFactory.getLogger(ForceCharge.class);
	private int chargePowerPercent;
	private int chargingTime;
	private int reachableMinCellVoltage;
	private int warningSoC;
	private LocalDateTime startTime = null;

	public ForceCharge(ManagedSymmetricEss ess, Battery bms, int chargePowerPercent, int chargingTime,
			int reachableMinCellVoltage, int warningSoC) {
		super(ess, bms);
		this.chargePowerPercent = chargePowerPercent;
		this.chargingTime = chargingTime;
		this.reachableMinCellVoltage = reachableMinCellVoltage;
		this.warningSoC = warningSoC;
	}

	@Override
	public State getState() {
		return State.FORCE_CHARGE;
	}

	@Override
	public State getNextState() {
		// According to the state machine the next states can be CHECK, FORCE_CHARGE or
		// UNDEFINED

		if (isNextStateUndefined()) {
			this.resetStartTime();
			return State.UNDEFINED;
		}

		if (this.startTime == null) {
			this.startTime = LocalDateTime.now();
		}

		if ( (isMinCellVoltageReached() && isWarningSoCReached()) || isChargingTimeOver()) {
			this.resetStartTime();
			return State.CHECK;
		}

		return State.FORCE_CHARGE;
	}

	private boolean isWarningSoCReached() {
		return getBmsSoC() >= warningSoC;
	}

	private boolean isMinCellVoltageReached() {
		return getBmsMinCellVoltage() >= reachableMinCellVoltage;
	}

	private boolean isChargingTimeOver() {
		return this.startTime.plusSeconds(chargingTime).isBefore(LocalDateTime.now());
	}

	private void resetStartTime() {
		this.startTime = null;

	}

	@Override
	public void act() throws OpenemsNamedException {
		log.info("act");
		chargeEssWithPercentOfMaxPower(chargePowerPercent);
	}
}