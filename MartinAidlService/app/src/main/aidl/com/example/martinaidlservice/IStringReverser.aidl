// IStringReverser.aidl
package com.example.martinaidlservice;

// Declare any non-default types here with import statements

interface IStringReverser {

    String reverseString(in String inString);

    int modifyBundle(in Bundle bundle);

    int modifyBundleReference(inout Bundle bundle);

}
