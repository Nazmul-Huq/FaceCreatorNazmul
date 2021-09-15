package com.example.facecreatornazmul;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class HelloApplication extends Application {
    static int width = 600;
    static int height = 600;
    static Canvas canvas = new Canvas(width, height);
    static GraphicsContext gc = canvas.getGraphicsContext2D();



    


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Group root = new Group();
        Scene scene = new Scene(root, width, height);
        stage.setTitle("Face");
        stage.setScene(scene);
        Scanner scanner = new Scanner(System.in) ;
        Random random = new Random();

        gc.clearRect(0, 0, width, height);
        root.getChildren().add(canvas);

        // ask user to choose face type
        System.out.println("How are you feeling today? Pleas choose one of the following: ");
        System.out.println("0 = don't know. Show me a random face");
        System.out.println("1 = neutral, 2 = happy, 3 = sad");
        System.out.println("4 = deep talk, 5 = shocked, 6 = surprised");
        System.out.println("7 = show all the faces randomly in every 1 sec");

        //checking the input for a number
        while (!scanner.hasNextInt()){
            String faceType = scanner.nextLine();
            System.out.println("You have entered other than number as: \"" + faceType + "\". Please enter a number");
        }
        int faceType= scanner.nextInt();

        //decidi which face to show (just one random/specific/random in every 1 sec)
         if(faceType == 0){ //will show a random face just once
            faceType = random.nextInt(6-1+1) +1;
            drawPrimitiveFace(faceType);
        } else if(faceType == 7){ // will show random faces in every 1 seconds
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                gc.clearRect(0, 0, width, height);
                try {
                    drawPrimitiveFace(random.nextInt(6-1+1) +1);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }));
            timeline.setCycleCount(360);
            timeline.play();
        } else if ((faceType ==1)|| (faceType ==2)|| (faceType ==3)|| (faceType ==4)|| (faceType ==5) || (faceType ==6)) { // will show specific face
            faceType = faceType;
            drawPrimitiveFace(faceType);
        } else {
             String errorMessage = errorMessage(faceType);
             System.out.println(errorMessage);
        }

        stage.show();
    } // end of "public void start(Stage stage) throws IOException"


    //drawing face by calling different methods
    public static void drawPrimitiveFace(int faceType) {
        //setting up initial variable
        String emojiName = ""; //variable for emoji name
        int mouthPositionX = 0; int mouthPositionY = 0; int mouthWidth = 0; int mouthHeight =  0; //declare variables to draw mouth
        int leftEyeWidth = 0; int leftEyeHeight = 0; // variables for left eye (note: left eye position is always fixed)
        int rightEyeWidth = 0; int rightEyeHeight = 0; // variables for right eye (note: right eye position is always fixed)
        int leftEyeBallPositionX = 0; int leftEyeBallPositionY = 0; int leftEyeBallWidth  = 0; int leftEyeBallHeight = 0; // variables for left eyeball
        int rightEyeBallPositionX = 0; int rightEyeBallPositionY = 0; int rightEyeBallWidth = 0; int rightEyeBallHeight = 0; //variables for right eyeball
        // to draw a curved lined face
        double CenterX = 0; double CenterY = 0; double RadiusX = 0; double RadiusY = 0; double StartAngle = 0; double Length = 0;

        switch (faceType){//based on user input this loop will choose one face at a time
            case 1: // neutral
                emojiName = "Neutral";
                leftEyeWidth = 50; leftEyeHeight = 50;
                rightEyeWidth = 50; rightEyeHeight = 50;
                mouthPositionX = 265; mouthPositionY = 350; mouthHeight = 90; mouthWidth = 30;
                break;
            case 2: //happy
                emojiName = "Happy";
                leftEyeWidth = 35; leftEyeHeight = 35;
                rightEyeWidth = 35; rightEyeHeight = 35;
                CenterX = 250; CenterY = 300; RadiusX = 100; RadiusY = 100; StartAngle = 180; Length = 180;
                break;
            case 3: //sad
                emojiName = "Sad";
                leftEyeWidth = 35; leftEyeHeight = 35;
                rightEyeWidth = 35; rightEyeHeight = 35;
                CenterX = 250; CenterY = 350; RadiusX = 100; RadiusY = 100; StartAngle = 00; Length = 180;
                break;
            case 4: //deep talk
                emojiName = "Deep Talk";
                leftEyeWidth = 50; leftEyeHeight = 50;
                rightEyeWidth = 50; rightEyeHeight = 50;
                CenterX = 220; CenterY = 200; RadiusX = 170; RadiusY = 170; StartAngle = 180; Length = 180;
                break;
            case 5: //shocking face
                emojiName = "Shocked";
                leftEyeWidth = 35; leftEyeHeight = 60;
                rightEyeWidth = 35; rightEyeHeight = 60;
                leftEyeBallPositionX = 235; leftEyeBallPositionY = 240; leftEyeBallWidth  = 15; leftEyeBallHeight = 15;
                rightEyeBallPositionX = 345; rightEyeBallPositionY = 240; rightEyeBallWidth = 15; rightEyeBallHeight = 15;
                mouthPositionX = 275; mouthPositionY = 350; mouthHeight = 40; mouthWidth = 70;
                break;
            case 6: //surprised
                emojiName = "Surprised";
                leftEyeWidth = 35; leftEyeHeight = 35;
                rightEyeWidth = 35; rightEyeHeight = 35;
                mouthPositionX = 260; mouthPositionY = 300; mouthHeight = 80; mouthWidth = 80;
                break;
            default:
                break;
        } // end of switch (faceType)

        drawShape();
        drawLeftEye(leftEyeWidth, leftEyeHeight);
        drawRightEye(rightEyeWidth, rightEyeHeight);
        drawLeftEyeBall(leftEyeBallPositionX, leftEyeBallPositionY, leftEyeBallWidth, leftEyeBallHeight);
        drawRightEyeBall(rightEyeBallPositionX, rightEyeBallPositionY, rightEyeBallWidth, rightEyeBallHeight);
        drawMouth(mouthPositionX, mouthPositionY, mouthHeight, mouthWidth);
        drawArcMouth(CenterX, CenterY, RadiusX, RadiusY, StartAngle, Length);
        emojiName(emojiName);
    }

    // set an error message if user choose a number that is out of range (right now rage is 0-7)
    public static String errorMessage(int faceType){
        String returnMessage = "You entered a face type that does not exist";
        return returnMessage;
    }

    // draw the round shape
    public static void drawShape() {
        gc.setFill(Color.YELLOW);
        gc.fillOval(150, 150, 300, 300);
    }

    //draw filled oval mouth
    public static void drawMouth(int mouthPositionX,  int mouthPositionY, int mouthWidth, int mouthHeight) {
        gc.setFill(Color.BLACK);
        gc.fillOval(mouthPositionX, mouthPositionY, mouthWidth, mouthHeight);
    }

    //draw curved and single lined mouth
    public static void drawArcMouth(double CenterX, double CenterY, double RadiusX, double RadiusY, double StartAngle, double Length){
        gc.strokeArc(CenterX, CenterY,  RadiusX,  RadiusY,  StartAngle, Length, ArcType.OPEN);
    }

    // draw left eye
    public static void drawLeftEye(int leftEyeWidth, int leftEyeHeight) {
        gc.setFill(Color.BLACK);
        gc.fillOval(220, 240, leftEyeWidth, leftEyeHeight);
    }

    //draw right eye
    public static void drawRightEye(int rightEyeWidth, int rightEyeHeight) {
        gc.setFill(Color.BLACK);
        gc.fillOval(340, 240, rightEyeWidth, rightEyeHeight);
    }

    //draw left eyeball
    public static void drawLeftEyeBall(int leftEyeBallPositionX, int leftEyeBallPositionY, int leftEyeBallWidth, int leftEyeBallHeight){
        gc.setFill(Color.WHITE);
        gc.fillOval(leftEyeBallPositionX, leftEyeBallPositionY, leftEyeBallWidth, leftEyeBallHeight);
    }

    //draw right eyeball
    public static void drawRightEyeBall(int rightEyeBallPositionX, int rightEyeBallPositionY, int rightEyeBallWidth, int rightEyeBallHeight) {
        gc.setFill(Color.WHITE);
        gc.fillOval(rightEyeBallPositionX, rightEyeBallPositionY, rightEyeBallWidth, rightEyeBallHeight);
    }

    // set emoji name
    public static void emojiName(String emojiName){
        gc.strokeText(emojiName, 270, 500);
    }

    public static void main(String[] args) {
        launch();
    }
}