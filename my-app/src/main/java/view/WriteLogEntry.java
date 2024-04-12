package view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.LogBuffer;
import model.Observable;
import model.Observer;

public class WriteLogEntry implements Observer {
    String d_fileName="log.txt";
    private static String d_bufferData;
    private FileWriter d_fileWriter;
    private BufferedWriter d_bufferedWriter;


    public WriteLogEntry() {

        try{
            d_fileWriter = new FileWriter(d_fileName);
            d_bufferedWriter = new BufferedWriter(d_fileWriter);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Observable o) {

        LogBuffer LogBuff = (LogBuffer)o;
        if(LogBuff.getGamePhaseSet()){

            d_bufferData =LogBuff.getPhaseValue();
            LogBuff.setGamePhaseSet(false);
        }
        else if(LogBuff.getCommandSet()){
            d_bufferData =LogBuff.getCommand();
            LogBuff.setCommandSet(false);
        }
        else if(LogBuff.getMessageSet()){
            d_bufferData =LogBuff.getMessage();
            LogBuff.setMessageSet(false);
        }

        try {
            d_bufferedWriter.newLine();
            d_bufferedWriter.write(d_bufferData.toString());
            d_bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

