package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
public class KickCommand extends Command{
    private FeederSubsystem m_feeder ;
    public KickCommand(FeederSubsystem fm){
        this.m_feeder = fm;
        addRequirements(m_feeder);
    }
    @Override
    public void initialize(){
        m_feeder.setFeeder(0);
    }
    @Override 
    public void execute(){
        m_feeder.setFeeder(0.3);
    }
    @Override
    public void end(boolean Interrupted){
        m_feeder.stop();
    }
    @Override
    public boolean isFinished(){
        return false;
    }
}
