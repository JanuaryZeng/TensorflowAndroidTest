# TensorflowAndroidTest
1.配置好sdk，ndk，android-studio

2.使用tensorflow导出pb文件

import tensorflow as tf
from tensorflow.python.framework import graph_util

session = tf.Session()

output_graph_def = graph_util.convert_variables_to_constants(session, session.graph_def,output_node_names=['output'])

with tf.gfile.FastGFile('D:/Item/app/src/main/assets/test.pb', mode='wb') as f:
    f.write(output_graph_def.SerializeToString())

session.close()

3.打开Android-studio，并创建一个assets文件夹,然后将导出得bp文件放入assets中
           
4.将下载或者编译好的jar&os文件放入libs文件夹中，之后再gradle中配置信息，首先在defaultConfig中填入以下信息：
        ndk {
            abiFilters "armeabi", "armeabi-v7a", "x86"
        }
只有这里面的cpu类型要和虚拟机的cpu类型匹配才能运行程序。然后再android中填入以下信息：
    sourceSets {
        main {
            jni.srcDirs = []
            jniLibs.srcDirs = ['libs']
        }
    }

这里是用来创建一个jniLibs文件夹，并将libs中的文件导入其中，以备调用。此时tensorflow的接口和所需使用的模型已经在Android-studio上配置好了。

5.编写接口程序

5.1首先导入tensorflow的api：

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

5.2在导入os文件：

    static {
        System.loadLibrary("tensorflow_inference");
    }

注意：在Windows下System.loadLibrary()函数导入的是dll文件，导入格式为${libname}.dll。在Linux下导入的是so文件，导入格式为lib${libname}.so。

