package com.alfuvedan.hrmanager.data.save;

import androidx.annotation.NonNull;

public interface ISavableData<T> {
    @NonNull T initFromLine(@NonNull String line);
    @NonNull String getSaveString();
}
