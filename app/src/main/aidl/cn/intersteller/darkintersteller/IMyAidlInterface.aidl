// IMyAidlInterface.aidl
package cn.intersteller.darkintersteller;

// Declare any non-default types here with import statements
import android.os.Bundle;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void sendStringData(String aString);
    void sendIntData(int aInt);
}
