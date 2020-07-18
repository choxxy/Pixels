package com.code.pixels;

import android.app.Application;
import org.koin.android.java.KoinAndroidApplication;
import org.koin.core.KoinApplication;
import org.koin.core.context.GlobalContext;
import static com.code.pixels.di.AppModuleKt.getAppModule;
import static org.koin.core.context.ContextFunctionsKt.startKoin;

public class PixelsApp extends Application {

    @Override
    public void onCreate(){
       super.onCreate();

        // Start Koin
        KoinApplication koin = KoinAndroidApplication.create(this)
                .modules(getAppModule());
        startKoin(new GlobalContext(), koin);
    }


}
