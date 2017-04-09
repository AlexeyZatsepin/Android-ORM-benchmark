package com.study.benchmarkorm;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = LibrariesDB.NAME, version = LibrariesDB.VERSION, foreignKeysSupported = true)
public class LibrariesDB {
    public static final String NAME = "LibrariesDB_DBFlow";
    public static final int VERSION = 1;


}