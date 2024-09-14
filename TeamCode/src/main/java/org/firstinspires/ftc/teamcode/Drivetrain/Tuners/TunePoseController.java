package org.firstinspires.ftc.teamcode.Drivetrain.Tuners;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.ejml.simple.SimpleMatrix;
import org.firstinspires.ftc.teamcode.Drivetrain.Drivetrain;

/**
 * THIS IS AN AUTONOMOUS OPMODE WE WILL USE TO TEST
 * YOUR DRIVETRAIN'S MOTOR DIRECTIONS.
 * MAKE SURE YOU ADD THE @CONFIG AT THE TOP OF ALL
 * YOUR TUNING/TESTING OPMODES. FTC DASHBOARD IS
 * BETTER THAN PRINTING ONTO THE PHONE VIA FTC SDK
 * TELEMETRY. DASHBOARD TELEMETRY IS BETTER!
 */
@Config
@Autonomous(name = "Test Pose", group = "Autonomous")
public class TunePoseController extends LinearOpMode {
    // Create drivetrain object
    Drivetrain drivetrain = null;

    // Use FTCDashboard
    FtcDashboard dashboard;
    public static double desiredX = 0;
    public static double desiredY = 0;
    public static double desiredTheta = 0;
    @Override
    public void runOpMode() {
        // Set dashboard
        drivetrain = new Drivetrain(hardwareMap);
        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();

        ElapsedTime looptime = new ElapsedTime();

        telemetry.addData("x", 0);
        telemetry.addData("y", 0);
        telemetry.addData("theta", 0);
        telemetry.addData("desiredX", 0);
        telemetry.addData("desiredY", 0);
        telemetry.addData("desiredTheta", 0);
        telemetry.update();
        waitForStart();
        looptime.reset();

        while (opModeIsActive()) {
            SimpleMatrix desiredPose = new SimpleMatrix(
                    new double [][]{
                            new double[]{desiredX},
                            new double[]{desiredY},
                            new double[]{desiredTheta}
                    }
            );
            drivetrain.localize();
            drivetrain.goToPose(desiredPose);
            telemetry.addData("x", drivetrain.state.get(0,0));
            telemetry.addData("y", drivetrain.state.get(1,0));
            telemetry.addData("theta", drivetrain.state.get(2,0));
            telemetry.addData("desiredX", desiredX);
            telemetry.addData("desiredY", desiredY);
            telemetry.addData("desiredTheta", desiredTheta);
            telemetry.update();
            looptime.reset();
        }
    }
}