package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import network.Network;

/**
 * Encapsulates the NavX on the robot. Use this class for determining
 * the angle of the robot.
 */
public class NavX extends SubsystemBase {
    private final AHRS ahrs;

    public NavX() {
        ahrs = new AHRS(SPI.Port.kMXP);
    }

    public double getHeading() {
        return ahrs.getCompassHeading();
    }

    public double getRoll() {
        return ahrs.getRoll();
    }

    public double getPitch() {
        return ahrs.getPitch();
    }

    public Rotation2d getRotation2d() {
        return ahrs.getRotation2d();
    }

    public void reset() {
        ahrs.reset();
    }

    @Override
    public void periodic() {
        Network.getTable("SmartDashboard").setString("heading", "%.2fdeg".formatted(getRotation2d().getDegrees()));
    }
}