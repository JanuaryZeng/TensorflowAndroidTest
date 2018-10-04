package com.example.january.tenandroidtest1;

import android.content.res.AssetManager;
import android.os.Trace;
import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.nio.FloatBuffer;

public class TensorAndroid {
    private static final String MODEL_FILE = "file:///android_asset/test.pb";
    private static final String inputName = "inputs";
    //用于存储的模型输入数据
    private float[] inputs;

    //模型中输出变量的名称
    private static final String outputName = "outputs";
    //用于存储模型的输出数据
    private float[] outputs = new float[1];

    TensorFlowInferenceInterface inferenceInterface;

    static {
        System.loadLibrary("tensorflow_inference");
    }

    TensorAndroid(AssetManager assetManager) {
        //接口定义
        inferenceInterface = new TensorFlowInferenceInterface(assetManager,MODEL_FILE);
    }

    public float[] getAddResult(float[] number) {
        //为输入数据赋值

        //将数据feed给tensorflow
        inferenceInterface.feed(inputName, number);

        //运行乘2的操作
        String[] outputNames = new String[] {outputName};
        inferenceInterface.run(outputNames);

        //将输出存放到outputs中
        inferenceInterface.fetch(outputName, outputs);

        return outputs;
    }
}

