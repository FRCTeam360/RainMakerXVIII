
import java.awt.image.DataBufferByte;

import org.opencv.core.Point;
import org.opencv.highgui.*;
import org.opencv.imgproc.*;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc.*;
import org.opencv.highgui.VideoCapture;

import java.net.Socket;

import java.util.ArrayList;

import java.awt.image.*;

import static java.lang.Math.tan;
import static org.opencv.imgproc.Imgproc.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
public class SimpleSample {
 /*     double targetHeight = 68.25;
        double cameraHeight = 14.5;
        double verticalFOV = 35;
        double horizontalFOV = 60;
        double cameraAngle = 49;
        double pi = 3.141592;

        ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        ArrayList<MatOfPoint> approx1 = new ArrayList<MatOfPoint>();
        MatOfPoint approx2 = new MatOfPoint();
        double y;
        double x;
        double distance;
        double azimuth;
        double great;
        Rect rec;
        Mat image = new Mat();
        Mat frame = new Mat();
        Mat heir = new Mat();
        for(;;) {
//            great = 0;
//            approx1.clear();
//            approx2 = new MatOfPoint();
//            cap.read(image);
//            GaussianBlur(image, image, new Size(5, 5), 0, 0);
//            if (image.empty()) {
//                return;
//            }
//            cvtColor(image, image, COLOR_BGR2HSV);convert to HSV
//            Core.inRange(image, new Scalar(55, 180, 75), new Scalar(110, 255, 185), frame);
            Imgproc.findContours(frame, contours, heir, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
		    System.out.println("hi");
            for (int i = 0; i < contours.size(); i++) {
                 rec = Imgproc.boundingRect(contours.get(i));
                if (rec.height > 25 && rec.width > 25) {
                    MatOfPoint2f otherContour = new MatOfPoint2f();
                    otherContour.fromArray((contours.get(i).toArray()));
                        great = rec.area();
                        approx1.add(approx2);
                        x = rec.br().x - rec.width / 2;
                        y = rec.br().y + rec.height / 2;
                        y = -((2 * (y / cap.get(CV_CAP_PROP_FRAME_HEIGHT))) - 1);
                        distance = (targetHeight - cameraHeight) / tan(((y * verticalFOV / 2 + cameraAngle) * pi / 180));
                        azimuth = (x / cap.get(CV_CAP_PROP_FRAME_WIDTH)) * horizontalFOV;
                        System.out.println(distance + " " + azimuth + " " + rec.br().x + " " + x + " " + rec.width);
                }
            }
        }
    }
}*/

    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    double targetHeight = 68.25;
    double cameraHeight = 14.5;
    double verticalFOV = 35;
    double horizontalFOV = 60;
    double cameraAngle = 49;
    double pi = 3.141592;

    ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
    ArrayList<Rect> rects = new ArrayList<Rect>();
    ArrayList<Rect> goodRects = new ArrayList<Rect>();
    ArrayList<Rect> myFavoriteRects = new ArrayList<Rect>();
    MatOfPoint2f approxContours = new MatOfPoint2f();
    MatOfPoint approx2 = new MatOfPoint();
    double closestRect ;
    double y;
    double x;
    double distance;
    double great;
    Mat input = new Mat();
    Mat blurred = new Mat();
    Mat HSVImage = new Mat();
    Mat thresholded = new Mat();
    double azimuth = 0;
    boolean shouldRun;
    public SimpleSample() {
        closestRect  = 1080;
        Runtime r = Runtime.getRuntime();
        Process p = null;
        try {
            p = r.exec("v4l2-ctl --set-ctrl=exposure_auto=1 -d /dev/video0");
            p.waitFor();
            p = r.exec("v4l2-ctl --set-ctrl=exposure_absolute=5 -d /dev/video0");
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RoboRIOConnection connection = new RoboRIOConnection();
        connection.start();
        Imshow im = new Imshow("Video Preview");
        im.Window.setResizable(true);
        Mat m = new Mat();
        VideoCapture vcam = new VideoCapture(0);
        while (vcam.isOpened() == false) {

        }

        while (m.empty()) {
            vcam.retrieve(m);
        }
        BufferedImage blank = new BufferedImage(m.width(), m.height(), BufferedImage.TYPE_BYTE_GRAY);
        while (true) {
            try {
                closestRect = 1080;
                myFavoriteRects.clear();
                goodRects.clear();
                contours.clear();
                rects.clear();
                vcam.read(input);
                if (input.empty()) {
                    return;
                }
                GaussianBlur(input, blurred, new Size(5, 5), 0, 0);
                cvtColor(blurred, HSVImage, COLOR_BGR2HSV);//convert to HSV
                Core.inRange(HSVImage, new Scalar(0, 40, 75), new Scalar(110, 255, 185), thresholded);
                Imgproc.findContours(thresholded, contours, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

                for (int i = 0; i < contours.size(); i++) {
                    //Imgproc.drawContours(input, contours, i, new Scalar(4, 4, 255));
                    rects.add(Imgproc.boundingRect(contours.get(i)));
                    if (rects.get(i).height > 30 && rects.get(i).width > 7) {
                        MatOfPoint2f contour2 = new MatOfPoint2f();
                        MatOfPoint2f approxContours = new MatOfPoint2f();
                        contour2.fromArray(contours.get(i).toArray());
                        Imgproc.approxPolyDP(contour2, approxContours, 6, true);
                        if (approxContours.total() == 4) {
                            Core.rectangle(input, new Point(rects.get(i).x, rects.get(i).y), new Point(rects.get(i).x + rects.get(i).width, rects.get(i).y + rects.get(i).height), new Scalar(255, 0, 0));
                            goodRects.add(rects.get(i));
                        }
                    }

                }
                double ydiff = 0;
                //System.out.println(goodRects.size());
                for (int i = 1; i < goodRects.size(); i++){
                         ydiff = Math.abs(goodRects.get(i-1).br().y - goodRects.get(i).br().y);
                        if (ydiff < closestRect ) {
                            myFavoriteRects.clear();
                            closestRect = ydiff;
                            myFavoriteRects.add(goodRects.get(i - 1));
                            myFavoriteRects.add(goodRects.get(i));
                            //System.out.println(myFavoriteRects.size());
                        }
                }
               if(closestRect < 1080) {
                    azimuth = (Math.abs((((myFavoriteRects.get(0).br().x - myFavoriteRects.get(0).width / 2) + (myFavoriteRects.get(1).br().x - myFavoriteRects.get(1).width / 2)) / 2) / vcam.get(Highgui.CV_CAP_PROP_FRAME_WIDTH) * 60) - 30);
               }
                im.showImage(input);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        SimpleSample test = new SimpleSample();
    }

    protected class RoboRIOConnection implements Runnable{
        Socket m_ReadSocket;
        BufferedReader inFromServer;
        long timeSinceLastMessage;
        String inputLine;
        Thread m_Thread;
        DataOutputStream outToServer;
        public RoboRIOConnection(){
            try {
                shouldRun = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            try{
                m_ReadSocket = new Socket("10.3.60.109", 3600);
                inFromServer = new BufferedReader(new InputStreamReader(m_ReadSocket.getInputStream()));
                outToServer = new DataOutputStream(m_ReadSocket.getOutputStream());
                send("Knock Knock Request");
                timeSinceLastMessage = System.currentTimeMillis();
            } catch (Exception e) {
                //e.printStackTrace();
            }
            while(m_ReadSocket != null && shouldRun && !m_ReadSocket.isClosed() && m_ReadSocket.isConnected() &&
                    System.currentTimeMillis() - timeSinceLastMessage < 333){
                try {
                    while(inFromServer.ready() && (inputLine = inFromServer.readLine()) != null){
                        System.out.println("received Message: " + inputLine);
                        if("Whos There".equals(decodeMessage("whosThere", inputLine))){
                            timeSinceLastMessage = System.currentTimeMillis();
                        } //else if("phoneType".equals(decodeMessage("messageType", inputLine))){
                        //   processInfoResponse(inputLine);
                        //}
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                if(System.currentTimeMillis() - timeSinceLastMessage > 100){
                    send("Knock Knock Request");
                }
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                send("<azimuth>" + ((Double)azimuth).toString() + "</azimuth>");
            }
            System.out.println("connection done");
            if(shouldRun){
                try{
                    Thread.sleep(333);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(shouldRun){
                    run();
                }
            }
        }
        public void processInfoResponse(String message){
            //if(message.equals(anObject))
            String phoneType;
            phoneType = decodeMessage("phoneType", message);
            if(phoneType.equals("vision")){
                // m_PhoneType = PhoneType.VISION;
            } else if(phoneType.equals("command")){
                //  m_PhoneType = PhoneType.COMMAND;
            } else if(phoneType.equals("commTester")){
                //  m_PhoneType = PhoneType.COMMSTEST;
            } else {
                //  m_PhoneType = PhoneType.UNKNOWN;
            }
        }
        public String decodeMessage(String tag, String message){
            int start;
            int stop;
            int tagLength;
            start = message.indexOf("<" + tag + ">");
            stop = message.indexOf("</" + tag + ">");
            tagLength = ("<" + tag + ">").length();
            if(start >= 0 && stop >= 0 && start + tagLength >= 0){
                return message.substring(start + tagLength, stop);
            } else {
                return "error";
            }
        }
        public void send(String message){
            try {
                outToServer.writeBytes(message + '\n');
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        public void start() {
            System.out.println("Starting " );
            if (m_Thread == null) {
                m_Thread = new Thread (this);
                m_Thread.start ();
            }
        }
    }
}
// credit to @master-atul
class Imshow {

    public JFrame Window;
    private ImageIcon image;
    private JLabel label;
    // private MatOfByte matOfByte;
    private Boolean SizeCustom;
    private int Height, Width;

    public Imshow(String title) {
        Window = new JFrame();
        image = new ImageIcon();
        label = new JLabel();
        // matOfByte = new MatOfByte();
        label.setIcon(image);
        Window.getContentPane().add(label);
        Window.setResizable(false);
        Window.setTitle(title);
        SizeCustom = false;
        setCloseOption(0);
    }

    public Imshow(String title, int height, int width) {
        SizeCustom = true;
        Height = height;
        Width = width;

        Window = new JFrame();
        image = new ImageIcon();
        label = new JLabel();
        // matOfByte = new MatOfByte();
        label.setIcon(image);
        Window.getContentPane().add(label);
        Window.setResizable(false);
        Window.setTitle(title);
        setCloseOption(0);

    }

    public void showImage(Mat img) {
        if (SizeCustom) {
            Imgproc.resize(img, img, new Size(Height, Width));
        }
        // Highgui.imencode(".jpg", img, matOfByte);
        // byte[] byteArray = matOfByte.toArray();
        BufferedImage bufImage = null;
        try {
            // InputStream in = new ByteArrayInputStream(byteArray);
            // bufImage = ImageIO.read(in);
            bufImage = toBufferedImage(img);
            image.setImage(bufImage);
            Window.pack();
            label.updateUI();
            Window.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CREDITS TO DANIEL: http://danielbaggio.blogspot.com.br/ for the improved
    // version !

    public BufferedImage toBufferedImage(Mat m) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster()
                .getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;

    }

    // Thanks to sutr90 for reporting the issue : https://github.com/sutr90

    public void setCloseOption(int option) {

        switch (option) {
            case 0:
                Window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                break;
            case 1:
                Window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                break;
            default:
                Window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

    }

    /**
     * Sets whether this window should be resizable or not, by default it is not
     * resizable
     *
     * @param resizable
     *            <code>true</code> if the window should be resizable,
     *            <code>false</code> otherwise
     */
    public void setResizable(boolean resizable) {
        Window.setResizable(resizable);
    }


    // Thanks to Jan Monterrubio for additional static methods for viewing images.


    /**
     * Displays the given {@link Mat} in a new instance of {@link Imshow}
     *
     * @param mat
     *            the {@link Mat} to display
     */
    public static void show(Mat mat) {
        show(mat, new Dimension(mat.rows(), mat.cols()), "", false,
                WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Displays the given {@link Mat} in a new instance of {@link Imshow} with
     * the given title as the title for the window
     *
     * @param mat
     *            the {@link Mat} to display
     * @param frameTitle
     *            the title for the frame
     */
    public static void show(Mat mat, String frameTitle) {
        show(mat, new Dimension(mat.rows(), mat.cols()), frameTitle, false,
                WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Displays the given {@link Mat} in a new instance of {@link Imshow} with
     * the given title as the title for the window and determines whether the
     * frame is resizable or not
     *
     * @param mat
     *            the {@link Mat} to display
     * @param frameTitle
     *            the title for the frame
     * @param resizable
     *            whether the frame should be resizable or not
     */
    public static void show(Mat mat, String frameTitle, boolean resizable) {
        show(mat, new Dimension(mat.rows(), mat.cols()), frameTitle, resizable,
                WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Displays the given {@link Mat} in a new instance of {@link Imshow} with a
     * set size
     *
     * @param mat
     *            the {@link Mat} to display
     * @param frameSize
     *            the size for the frame
     */
    public static void show(Mat mat, Dimension frameSize) {
        show(mat, frameSize, "", false, WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Displays the given {@link Mat} in a new instance of {@link Imshow} with a
     * set size and given title
     *
     * @param mat
     *            the {@link Mat} to display
     * @param frameSize
     *            the size for the frame
     * @param frameTitle
     *            the title for the frame
     */
    public static void show(Mat mat, Dimension frameSize, String frameTitle) {
        show(mat, frameSize, frameTitle, false, WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Displays the given {@link Mat} in a new instance of {@link Imshow} with a
     * set size and given title and whether it is resizable or not
     *
     * @param mat
     *            the {@link Mat} to display
     * @param frameSize
     *            the size for the frame
     * @param frameTitle
     *            the title for the frame
     */
    public static void show(Mat mat, Dimension frameSize, String frameTitle,
                            boolean resizable) {
        show(mat, frameSize, frameTitle, resizable,
                WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Displays the given {@link Mat} in a new instance of {@link Imshow} with a
     * set size and given title and whether it is resizable or not, and with the
     * close operation set
     *
     * @param mat
     *            the {@link Mat} to display
     * @param frameSize
     *            the size for the frame
     * @param frameTitle
     *            the title for the frame
     * @param resizable
     *            wether the frame is resizable or not
     * @param closeOperation
     *            the constant for the default close operation of the frame
     */
    public static void show(Mat mat, Dimension frameSize, String frameTitle,
                            boolean resizable, int closeOperation) {
        Imshow frame = new Imshow(frameTitle, frameSize.height, frameSize.width);
        frame.setResizable(resizable);

		/*
		 * This is a bad way to access the window, but due to legacy stuff I
		 * won't change the access patterns
		 */
        frame.Window.setDefaultCloseOperation(closeOperation);
        frame.showImage(mat);
    }

}
