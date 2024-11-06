package frc.robot.commands;

import edu.wpi.first.math.controller.BangBangController;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends Command{
    private ShooterSubsystem m_shooter ;
    private FeederSubsystem m_feeder ;
    private HopperSubsystem m_hopper;
    private SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0.64665, 0.10772, 0.037027);
    private BangBangController shooterBang = new BangBangController(5);
    double limelightMountAngleDegrees = 33.0;
    double limelightLensHeightInches = 23.5;
    double goalHeightInches = 104.0;
    // private PIDController shooterPID = new PIDController(0.00026,0.00006,0.0);
    private double desiredSpeed =100;//6000
    public ShootCommand(ShooterSubsystem m_shooter, FeederSubsystem m_feeder, HopperSubsystem m_hopper){
        this.m_shooter = m_shooter;
        this.m_feeder = m_feeder;
        this.m_hopper = m_hopper;
        // shooterPID.setTolerance(50);
        addRequirements(m_shooter);
    }
    public double calculate(){
        return shooterBang.calculate(m_shooter.getRate(),desiredSpeed)*2+0.0006*feedforward.calculate(desiredSpeed);
    }
    @Override
    public void initialize(){
        m_shooter.setShooter(0);
        m_feeder.setFeeder(0);
        m_hopper.setHopper(0);
    }
    @Override
    public void execute(){
        //m_shooter.setShooter(0.5);
        m_shooter.setShooter(calculate());
        //jasper writes code
        m_feeder.setFeeder(0.3);
                if(m_shooter.getRate()>=desiredSpeed*1.1){
                    m_feeder.setFeeder(2000);//0.6
                    m_hopper.setHopper(0.5);

            
        }else{
            m_feeder.stop();
            m_hopper.stopHopper();
        }
    }
    @Override
    public void end(boolean interrupted){
        m_shooter.stopShooter();
        m_feeder.stop();
        m_hopper.stopHopper();
    }
    @Override 
    public boolean isFinished(){
        return false;
    }

}




// package frc.robot.commands;

// import edu.wpi.first.wpilibj.util.Color;
// import edu.wpi.first.wpilibj.util.Color8Bit;
// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.led.LEDStrip;
// import frc.robot.subsystems.ShooterSubsystem;

// public class ShootCommand extends Command {
//     private final ShooterSubsystem shooterSubsystem;
//     private final LEDStrip ledStrip;

//     public ShootCommand(ShooterSubsystem shooterSubsystem, LEDStrip ledStrip) {
//         this.shooterSubsystem = shooterSubsystem;
//         this.ledStrip = ledStrip;
//         addRequirements(shooterSubsystem);
//     }

//     @Override
//     public void execute() {
//         shooterSubsystem.run();
//         ledStrip.usePattern((led, time) -> {
//             double s = 12;
//             double t = Math.asin(Math.sin(led * 0.3 + time * s)) * 2 / Math.PI + 1;
//             Color c = Color.fromHSV((int)(
//                     t * 10 + 170
//             ), 255, 255);
//             return new Color8Bit(c);
//         });
//     }

//     @Override
//     public void end(boolean interrupted) {
//         shooterSubsystem.stop();
//     }
// }
