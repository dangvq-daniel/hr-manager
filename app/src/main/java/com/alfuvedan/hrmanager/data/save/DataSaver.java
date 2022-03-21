package com.alfuvedan.hrmanager.data.save;

import java.io.*;
import java.util.*;

public class DataSaver {
    public static <T extends ISavableData> void saveData(File saveFile, Collection<T> dataCollection) throws IOException {
        if(saveFile.createNewFile())
            System.out.println("Created file " + saveFile);

        FileOutputStream outStream = new FileOutputStream(saveFile);

        for(T data : dataCollection) {
            String[] strVals = data.getSaveData();
            StringBuilder dataRow = new StringBuilder();

            for(int i = 0; i < strVals.length; i++) {
                dataRow.append(strVals[i]);
                dataRow.append(i < strVals.length - 1 ? ',' : '\n');
            }

            byte[] dataArr = dataRow.toString().getBytes();
            outStream.write(dataArr);
        }

        outStream.close();
    }

    public static <T extends ISavableData> Collection<T> readData(File saveFile, IDataConverter<T> getData) throws IOException {
        Collection<T> collection = new ArrayList<>();

        if(!saveFile.exists())
            return collection;

        FileInputStream inStream = new FileInputStream(saveFile);
        InputStreamReader inStreamReader = new InputStreamReader(inStream);
        BufferedReader bufReader = new BufferedReader(inStreamReader);

        String line;
        while((line = bufReader.readLine()) != null) {
            String[] row = line.split(",");
            collection.add(getData.getFromCSVRow(row));
        }

        bufReader.close();

        return collection;
    }
}
