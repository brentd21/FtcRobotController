package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
//import com.acmerobotics.roadrunner.Actions;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.ejml.simple.SimpleMatrix;
import org.firstinspires.ftc.teamcode.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Drivetrain.Utils.TelemetryTracking;
/**
 * THIS IS AN AUTONOMOUS OPMODE WE WILL USE TO TEST
 * YOUR DRIVETRAIN'S MOTOR DIRECTIONS.
 * MAKE SURE YOU ADD THE @CONFIG AT THE TOP OF ALL
 * YOUR TUNING/TESTING OPMODES. FTC DASHBOARD IS
 * BETTER THAN PRINTING ONTO THE PHONE VIA FTC SDK
 * TELEMETRY. DASHBOARD TELEMETRY IS BETTER!
 */
@Config
@Autonomous(name = "Test AAaAAAAAAAA", group = "Autonomous")
public class TestAuton extends LinearOpMode {
    // Create drivetrain object
    Drivetrain drivetrain = null;
    // Use FTCDashboard
    FtcDashboard dashboard;
    TelemetryTracking tracking;
    public static double desiredX = 0;
    public static double desiredY = 0;
    public static double desiredTheta = 0;
    @Override
    public void runOpMode() {
        // Set dashboard
        drivetrain = new Drivetrain(hardwareMap);
        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        tracking = new TelemetryTracking();
        ElapsedTime looptime = new ElapsedTime();

        telemetry.addData("x", 0);
        telemetry.addData("y", 0);
        telemetry.addData("theta", 0);
        telemetry.addData("desiredX", 0);
        telemetry.addData("desiredY", 0);
        telemetry.addData("desiredTheta", 0);
        telemetry.update();
        drivetrain.resetOp();
        waitForStart();
        looptime.reset();
        while (opModeIsActive()) {
            SimpleMatrix desiredPose = new SimpleMatrix(
                    new double [][]{
                            new double[]{48},
                            new double[]{0},
                            new double[]{0}
                    }
            );
            SimpleMatrix desiredPoseTwo = new SimpleMatrix(
                    new double [][]{
                            new double[]{0},
                            new double[]{-12},
                            new double[]{0}
                    }
            );
            drivetrain.localize();
            if (drivetrain.opSwitch == 0){
                Actions.runBlocking(drivetrain.goToPose(desiredPose));
            } else {
                Actions.runBlocking(drivetrain.goToPose(desiredPoseTwo));
            }
            telemetry.addData("x", drivetrain.state.get(0,0));
            telemetry.addData("y", drivetrain.state.get(1,0));
            telemetry.addData("theta", drivetrain.state.get(2,0));
            telemetry.addData("desiredX", desiredX);
            telemetry.addData("desiredY", desiredY);
            telemetry.addData("desiredTheta", desiredTheta);
            telemetry.addData("uLf", drivetrain.motorController.uLf);
            telemetry.addData("uLb", drivetrain.motorController.uLb);
            telemetry.addData("uRb", drivetrain.motorController.uRb);
            telemetry.addData("uRf", drivetrain.motorController.uRf);
            telemetry.addData("state", drivetrain.opSwitch);
            //dashboard.sendTelemetryPacket(tracking.updatePos(drivetrain.state.get(0,0), drivetrain.state.get(1,0), drivetrain.state.get(2,0)));
            TelemetryPacket packet = new TelemetryPacket();
            packet.fieldOverlay();
            packet.put("x", drivetrain.state.get(0,0));
            packet.put("y", drivetrain.state.get(1,0));
            packet.put("heading", drivetrain.state.get(2,0));
            telemetry.update();
            looptime.reset();
        }
    }
}