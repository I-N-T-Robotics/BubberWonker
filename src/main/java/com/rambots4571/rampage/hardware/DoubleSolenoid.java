package com.rambots4571.rampage.hardware;

/**
 * Wrapper class for {@link edu.wpi.first.wpilibj.DoubleSolenoid} allows
 * to easily toggle between values
 * {@link edu.wpi.first.wpilibj.DoubleSolenoid.Value}
 */
public class DoubleSolenoid extends edu.wpi.first.wpilibj.DoubleSolenoid {
    public DoubleSolenoid(int forwardChannel, int reverseChannel) {
        super(forwardChannel, reverseChannel);
    }

    public final void toggle() {
        Value currentValue = this.get() == Value.kForward ?
                             Value.kReverse : Value.kForward;
        this.set(currentValue);
    }
}
