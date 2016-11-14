/*******************************************************************************
 * OpenEMS - Open Source Energy Management System
 * Copyright (c) 2016 FENECON GmbH and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *   FENECON GmbH - initial API and implementation and initial documentation
 *******************************************************************************/
package io.openems.api.device.nature.meter;

import io.openems.api.channel.ReadChannel;

public interface AsymmetricMeterNature extends MeterNature {

	/*
	 * ReadChannels
	 */
	public ReadChannel<Long> activePowerL1();

	public ReadChannel<Long> activePowerL2();

	public ReadChannel<Long> activePowerL3();

	public ReadChannel<Long> reactivePowerL1();

	public ReadChannel<Long> reactivePowerL2();

	public ReadChannel<Long> reactivePowerL3();

	public ReadChannel<Long> currentL1();

	public ReadChannel<Long> currentL2();

	public ReadChannel<Long> currentL3();

	public ReadChannel<Long> voltageL1();

	public ReadChannel<Long> voltageL2();

	public ReadChannel<Long> voltageL3();
}
