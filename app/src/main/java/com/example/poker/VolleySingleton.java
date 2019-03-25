package com.example.poker;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton myVolley = null; //Para acceder a la variable sin instanciar la clase
    private RequestQueue myrequest;

    private VolleySingleton(Context context)
    {
        myrequest = Volley.newRequestQueue(context);
    }

    public static VolleySingleton getInstance(Context context)
    {
        if (myVolley == null)
        {
            myVolley = new VolleySingleton(context);
        }
        return  myVolley;
    }
    public RequestQueue getRequestQueue()
    {
        return myrequest;
    }
}
