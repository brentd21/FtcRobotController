package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.ejml.simple.SimpleMatrix;
import org.firstinspires.ftc.teamcode.Drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.Drivetrain.Utils.Utils;

@Config
@TeleOp(name = "TeleOp", group = "Autonomous")
public class OpModeTest extends LinearOpMode {

    Drivetrain drivetrain = null;
    // Use FTCDashboard
    FtcDashboard dashboard;

    public void runOpMode() {
        //  Up here you initialize servos or anything that relates to setting positions
        //  Also you instantiate your drivetrain and dashboard
        drivetrain = new Drivetrain(hardwareMap);
        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();

        waitForStart();
        if (isStopRequested()) return;  // This will stop your opMode and all code so your
                                        // bot doesn't go out of control

        // ---------Everything under this comment will be your actual start ---------


    }


}