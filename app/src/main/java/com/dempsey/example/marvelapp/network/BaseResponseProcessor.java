package com.dempsey.example.marvelapp.network;

import android.support.annotation.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class BaseResponseProcessor {

    @NonNull
    public static <T> T process(@NonNull final Call<T> call) throws Exception {
        final Response<T> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new RemoteException(response.code(), response.message(), response.errorBody());
        }
    }

    private static class RemoteException extends Exception {

        private final int code;
        private final ResponseBody body;

        RemoteException(final int code, @NonNull final String message, @NonNull final ResponseBody body) {
            super(message);
            this.code = code;
            this.body = body;
        }

        public int code() {
            return code;
        }

        public ResponseBody body() {
            return body;
        }
    }
}
