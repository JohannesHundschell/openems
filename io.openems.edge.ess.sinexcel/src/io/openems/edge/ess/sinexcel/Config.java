package io.openems.edge.ess.sinexcel;

import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import org.osgi.service.metatype.annotations.AttributeDefinition;

@ObjectClassDefinition( //
		name = "ESS Sinexcel", //
		description = "Implements the Sinexcel battery inverter.")
@interface Config {

	@AttributeDefinition(name = "Component-ID", description = "Unique ID of this Component")
	String id() default "ess0";

	@AttributeDefinition(name = "Alias", description = "Human-readable name of this Component; defaults to Component-ID")
	String alias() default "";

	@AttributeDefinition(name = "Is enabled?", description = "Is this Component enabled?")
	boolean enabled() default true;

	@AttributeDefinition(name = "Modbus-ID", description = "ID of Modbus brige.")
	String modbus_id();

	@AttributeDefinition(name = "Modbus target filter", description = "This is auto-generated by 'Modbus-ID'.")
	String Modbus_target() default "";

	@AttributeDefinition(name = "Battery-ID", description = "ID of Battery.")
	String battery_id();

	@AttributeDefinition(name = "Battery target filter", description = "This is auto-generated by 'Battery-ID'.")
	String Battery_target() default "";
	
	@AttributeDefinition(name = "Topping charge", description = "The topping charge voltage is the voltage that the battery supposed to finally reach in the charging procedure")
    int toppingCharge() default 4370;	
	
	// input channel ------------------------
	@AttributeDefinition(name = "DigitalInput", description = "Input channel 1 - indicates Bender waiting or not")
    String digitalInput1() default "io0/DigitalInputM1C1";
	
	@AttributeDefinition(name = "DigitalInput", description = "Input channel 2 - which specify the gridmode")
    String digitalInput2() default "io0/DigitalInputM1C2";
	
	@AttributeDefinition(name = "DigitalInput", description = "Input channel 3")
    String digitalInput3() default "io0/DigitalInputM2C1";
	
	@AttributeDefinition(name = "DigitalInput", description = "Input channel 4")
    String digitalInput4() default "io0/DigitalInputM2C2";
	
	// output channel ------------------------
	@AttributeDefinition(name = "DigitalInput", description = "Output channel 1")
    String digitalOutput1() default "io0/DigitalInputM3C1";
	
	@AttributeDefinition(name = "DigitalInput", description = "Output channel 2")
    String digitalOutput2() default "io0/DigitalInputM3C2";
	
	@AttributeDefinition(name = "DigitalInput", description = "Output channel 3")
    String digitalOutput3() default "io0/DigitalInputM4C1";
	
	@AttributeDefinition(name = "DigitalInput", description = "Output channel 4")
    String digitalOutput4() default "io0/DigitalInputM4C2";
	
 	@AttributeDefinition(name = "Start and stop", description = "Turn ON and turn OFF the Inverter")
	InverterState InverterState() default InverterState.ON;

	String webconsole_configurationFactory_nameHint() default "ESS Sinexcel [{id}]";
}