package org.usfirst.frc.team360.robot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
public class RPIConnection {
    byte[] bytesOfMat;
 	byte[] incomingBytes;
 	Mat input = new Mat();
    boolean shouldRun;
    Socket dataCommSocket;
    ServerSocket dataCommServerSocket;
    DataOutputStream dataCommOutput;
    BufferedReader dataCommInput;
    Socket visionSocket;
    ServerSocket visionServerSocket;
    DataOutputStream visionOutput;
    BufferedReader visionInput;
    String dataCommInputString = "";
    String visionInputString = "";
    long timeSinceLastMessage = 0;
    Thread readThread;
    int start;
    int stop;
    int tagLength;
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    CvSink cvSink = CameraServer.getInstance().getVideo();
    CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 320, 240);
    Mat source = new Mat();
    double[] bytes;
    double[] bytes2 = new double[921600];
    String[] inputDoubles = new String[0];
    ObjectInputStream objectInput;
	//UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	public RPIConnection(){
		try{
		System.out.println("started connection");
		shouldRun = true;
		RPIConnectionThread con = new RPIConnectionThread();
		con.start(); 
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void shutDown(){
		shouldRun = false;
	}
	
	  
	 protected class RPIConnectionThread extends Thread {
	        public RPIConnectionThread() {
	        	//readThread = new Thread(new ReadThread());
            	//readThread.start();
	            shouldRun = true;
	        }
	        public void run() {
	            try {
	                dataCommServerSocket = new ServerSocket(3600);
	                dataCommServerSocket.setReuseAddress(true);
	                dataCommSocket = dataCommServerSocket.accept();
	                dataCommSocket.setReuseAddress(true);
	                dataCommInput = new BufferedReader(new InputStreamReader(dataCommSocket.getInputStream()));
	                dataCommOutput = new DataOutputStream(dataCommSocket.getOutputStream());
	                timeSinceLastMessage = System.currentTimeMillis();
	            } catch (Exception e) {
	                System.out.println("error " + e.toString());
	            }
	            while (dataCommSocket != null && !dataCommSocket.isClosed() && dataCommSocket.isConnected() && shouldRun &&
	                    System.currentTimeMillis() - timeSinceLastMessage < 333) {
	            	//if(joy.getRawButton(1) == true){
	            		send("<VisionModeRequest>" + "GearDriverVision" + "</VisionModeRequest>");
//	            	} else if(joy.getRawButton(2) == true){
//	            		send("<VisionModeRequest>" + "GearAutoTarget" + "</VisionModeRequest>");
//	            	} else if(joy.getRawButton(3) == true){
//	            		send("<VisionModeRequest>" + "BoilerAutoTarget" + "</VisionModeRequest>");
//	            	} else if(joy.getRawButton(4) == true){
//	            		send("<VisionModeRequest>" + "BoilderDriverVision" + "</VisionModeRequest>");
//	            	} 
	                try {
	                    while (dataCommInput.ready() && (dataCommInputString = dataCommInput.readLine()) != null) {
	                        //System.out.println(clientSentence);
	                    	if (containsTag("KnockKnock", dataCommInputString)) {
	                       //     System.out.println("Recieved Knock Knock Request");
	                            send(createWhosThereTag());
	                            timeSinceLastMessage = System.currentTimeMillis();
	                        } else if(containsTag("TargetInfo", dataCommInputString)){
	                        	System.out.println("Target Info" + decodeMessage("TargetInfo", dataCommInputString));
	                        }
	                    }
	                } catch (Exception e) {
	                    // TODO Auto-generated catch block
	                    System.out.println("error " + e.toString());
	                }
	                try {
	                    Thread.sleep(20);
	                } catch (Exception e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	            try {
	                dataCommServerSocket.close();
	                dataCommSocket.close();
	            } catch (Exception e) {
	                System.out.println("error " + e.toString());
	            }
	            System.out.println("done");
	            if (shouldRun) {
	                try {
	                    if (shouldRun) {
	                        Thread.sleep(100);
	                    }
	                } catch (Exception e) {
	                    System.out.println("error " + e.toString());
	                }
	                run();
	            }
	        }

	        public String createInfoResponse() {
	            return createMessageTypeTag("phoneType") + createTaggedMessage("phoneType", "vision");
	        }

	        public String createWhosThereTag() {
	            return createTaggedMessage("whosThere", "Whos There");
	        }

	        public String createMessageTypeTag(String messageType) {
	            return createTaggedMessage("messageType", messageType);
	        }
	        public boolean containsTag(String tag, String message){
	        	return message.contains("<" + tag + ">") && message.contains("</" + tag + ">");
	        }
	        public String createTaggedMessage(String tag, String message) {
	            return "<" + tag + ">" + message + "</" + tag + ">";
	        }
	        public String decodeMessage(String tag, String message){
	            start = message.indexOf("<" + tag + ">");
	            stop = message.indexOf("</" + tag + ">");
	            tagLength = ("<" + tag + ">").length();
	            if(start >= 0 && stop >= 0 && start + tagLength >= 0){
	                return message.substring(start + tagLength, stop);
	            } else {
	                return "error";
	            }
	        }
	        public void send(String message) {
	            try {
	                dataCommOutput.writeBytes(message + '\n');
	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                System.out.print(e);
	            }
	        }
	        protected class ReadThread implements Runnable{

				@Override
				public void run() {
					try{
						visionServerSocket = new ServerSocket(3601);
						visionServerSocket.setReuseAddress(true);
						visionSocket = visionServerSocket.accept();
						visionSocket.setReuseAddress(true);
						//visionInput = new BufferedReader(new InputStreamReader(visionSocket.getInputStream()));
						visionOutput = new DataOutputStream(visionSocket.getOutputStream());
						objectInput = new ObjectInputStream(visionSocket.getInputStream());
					}catch (Exception e) {
	                    System.out.println("error " + e.toString());
	                }
					input = new Mat(240, 320, CvType.CV_64FC3);
//					while (visionSocket != null && !visionSocket.isClosed() && visionSocket.isConnected() && shouldRun &&
//		                    System.currentTimeMillis() - timeSinceLastMessage < 333) {
//						try{
//							while (visionInput.ready() && (visionInputString = visionInput.readLine()) != null) {
////								for(int  i = 0; i < inputDoubles.length; i++){
////									bytes2[i] = Double.parseDouble(inputDoubles[i]);
////								}
////								input.put(0, 0, bytes2);
////								outputStream.putFrame(input);
//								System.out.println("dasdsadsasdasadsdasaddssadsaddasdasdd");
//							}
//						} catch (Exception e) {
//		                    // TODO Auto-generated catch block
//							System.out.println("error " + e.toString());
//						}
//						try {
//							Thread.sleep(20);
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
					input = new Mat(240, 320, CvType.CV_64FC3);
					while (visionSocket != null && !visionSocket.isClosed() && visionSocket.isConnected() && shouldRun &&
		                    System.currentTimeMillis() - timeSinceLastMessage < 333) {
						try{
							
							String thing = (String)objectInput.readObject();
							System.out.println(thing);
						}catch (Exception e) {
		                    // TODO Auto-generated catch block
							System.out.println("error " + e.toString());
						}
					}
					try {
						visionServerSocket.close();
						visionSocket.close();
					} catch (Exception e) {
						System.out.println("error " + e.toString());
					}
					if (shouldRun) {
						try {
							if (shouldRun) {
								Thread.sleep(100);
							}
						} catch (Exception e) {
							System.out.println("error " + e.toString());
						}
						run();
					}	
				}
	        }
	    }
	 
	    public String encodeString(String tag, String message){
	        return "@" + tag + "$" + message + "%";
	    }
}