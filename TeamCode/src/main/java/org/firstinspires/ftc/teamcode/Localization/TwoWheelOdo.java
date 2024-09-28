/*   MIT License
 *   Copyright (c) [2024] [Base 10 Assets, LLC]
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:

 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.

 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.Localization;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.ejml.simple.SimpleMatrix;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Devices.GoBildaPinpointDriver;

import java.util.Locale;

@Config
public class TwoWheelOdo {

    GoBildaPinpointDriver odo;
    public static double xOffset = 138.0;
    public static double yOffset = 0.0;
    HardwareMap hwMap;
    public TwoWheelOdo(HardwareMap hardwareMap){
        hwMap = hardwareMap;
        odo = hwMap.get(GoBildaPinpointDriver.class,"odo");
        odo.setOffsets(-xOffset, yOffset); //these are tuned for 3110-0002-0001 Product Insight #1
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.REVERSED);
        odo.resetPosAndIMU();
    }
        public SimpleMatrix calculate(){
            odo.update();
            return new SimpleMatrix(
                    new double[][]{
                            new double[]{odo.getPosition().getX(DistanceUnit.INCH)},
                            new double[]{odo.getPosition().getY(DistanceUnit.INCH)},
                            new double[]{odo.getHeading()},
                            new double[]{odo.getVelocity().getX(DistanceUnit.INCH)},
                            new double[]{odo.getVelocity().getY(DistanceUnit.INCH)},
                            new double[]{odo.getHeadingVelocity()}
                    }
            );
        }
        public void resetPosAndRecalibrateIMU(){
            odo.resetPosAndIMU();
        }
        public void reCalibrateIMU(){
            odo.recalibrateIMU();
        }
    }