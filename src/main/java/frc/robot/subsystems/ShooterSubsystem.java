package frc.robot.subsystems;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase{
    private TalonFX ShooterMotor = new TalonFX(Constants.ShooterMotor);

    public ShooterSubsystem(){
        ShooterMotor.setNeutralMode(NeutralModeValue.Coast);
        // ShooterMotor.configVoltageCompSaturation(12);
        // ShooterMotor.enableVoltageCompensation(true);
        ShooterMotor.getConfigurator().apply(new CurrentLimitsConfigs().withStatorCurrentLimit(4));
        // ShooterMotor.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true,35,40,1.0));
        // ShooterMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true,30,35,0.5));
        //ShooterMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        
        
    }
    public void setShooter(double speed){
        if(!Constants.climbing){
        //ShooterMotor.set(speed);
        ShooterMotor.set(-0.4);//switched from negative
        
        }else{
            ShooterMotor.set(0);
        }
    }
    public void setShooterVolt(double volts){
        ShooterMotor.setVoltage(-volts);
    }
    public void stopShooter(){
        ShooterMotor.set(0);
    }
    public double getRate(){
        return ((-ShooterMotor.getVelocity().getValue()));
    }
    // public double getPosition(){
    //     return -ShooterMotor.getSelectedSensorPosition();
    // } 
    public void startClimbing(){
        Constants.climbing = true;
    }
    public void stopClimbing(){
        Constants.climbing = false;
    }
}