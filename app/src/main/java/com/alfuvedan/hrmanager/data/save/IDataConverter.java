package com.alfuvedan.hrmanager.data.save;

public interface IDataConverter<T extends ISavableData> {
    T getFromCSVRow(String[] row);
}
