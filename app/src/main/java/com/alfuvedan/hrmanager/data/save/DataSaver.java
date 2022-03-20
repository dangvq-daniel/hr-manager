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
    }
}
