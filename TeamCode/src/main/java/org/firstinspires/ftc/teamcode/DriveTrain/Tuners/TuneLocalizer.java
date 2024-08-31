package org.firstinspires.ftc.teamcode.DriveTrain.Tuners;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.DriveTrain.Drivetrain;

/**
 * THIS IS AN AUTONOMOUS OPMODE WE WILL USE TO TEST
 * YOUR DRIVETRAIN'S MOTOR DIRECTIONS.
 * MAKE SURE YOU ADD THE @CONFIG AT THE TOP OF ALL
 * YOUR TUNING/TESTING OPMODES. FTC DASHBOARD IS
 * BETTER THAN PRINTING ONTO THE PHONE VIA FTC SDK
 * TELEMETRY. DASHBOARD TELEMETRY IS BETTER!
 */
@Config
@Autonomous(name = "Test Localizer", group = "Autonomous")
public class TuneLocalizer extends LinearOpMode {
    // Create drivetrain object
    Drivetrain drivetrain = null;

    // Use FTCDashboard
    FtcDashboard dashboard;




    @Override
    public void runOpMode() {
        // Set dashboard
        drivetrain = new Drivetrain(hardwareMap);
        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();

        ElapsedTime looptime = new ElapsedTime();


        waitForStart();

        looptime.reset();

        while (opModeIsActive()) {
            drivetrain.localize();


            telemetry.addLine("Looptime [ms]: " + looptime.milliseconds());
            telemetry.addLine("Left Encoder [ticks]: " + (drivetrain.deadWheelOdo.currentLeftRawPos));
            telemetry.addLine("Right Encoder [ticks]: " + (drivetrain.deadWheelOdo.currentRightRawPos));
            telemetry.addLine("Horizontal Encoder [ticks]: " + (drivetrain.deadWheelOdo.currentCenterRawPos));
            telemetry.addLine("X [in]: " + (drivetrain.state.get(0, 0)));
            telemetry.addLine("Y [in]: " + (drivetrain.state.get(1, 0)));
            telemetry.addLine("Theta [deg]: " + Math.toDegrees(drivetrain.state.get(2, 0)));
            telemetry.update();


            looptime.reset();
        }
    }
}