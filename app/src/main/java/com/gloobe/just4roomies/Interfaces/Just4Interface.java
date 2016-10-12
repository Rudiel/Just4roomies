package com.gloobe.just4roomies.Interfaces;

import com.gloobe.just4roomies.Modelos.AddRoom;
import com.gloobe.just4roomies.Modelos.AddUser;
import com.gloobe.just4roomies.Modelos.Model_Chat_Conversacion;
import com.gloobe.just4roomies.Modelos.Model_Chat_Mensaje;
import com.gloobe.just4roomies.Modelos.Model_Chat_Mensaje_Response;
import com.gloobe.just4roomies.Modelos.Model_Chat_Response;
import com.gloobe.just4roomies.Modelos.Model_Contact;
import com.gloobe.just4roomies.Modelos.Model_EliminarChat;
import com.gloobe.just4roomies.Modelos.Model_Like;
import com.gloobe.just4roomies.Modelos.Model_Like_Response;
import com.gloobe.just4roomies.Modelos.Model_Perfiles;
import com.gloobe.just4roomies.Modelos.Model_SolicitudAceptar;
import com.gloobe.just4roomies.Modelos.Model_UserUpdate;
import com.gloobe.just4roomies.Modelos.RespuestaLoginFB;
import com.gloobe.just4roomies.Modelos.RespuestaUsuario;
import com.gloobe.just4roomies.Modelos.SocialLogin;
import com.gloobe.just4roomies.Modelos.UpdateRoom;
import com.gloobe.just4roomies.Modelos.UserUpdate;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by rudielavilaperaza on 25/08/16.
 */
public interface Just4Interface {

    @Headers({"Content-Type: application/json"})
    @POST("adduser")
    Call<RespuestaUsuario> registarUsuario(@Body AddUser user);

    @Headers({"Content-Type: application/json"})
    @POST("socialid")
    Call<RespuestaLoginFB> socialLogin(@Body SocialLogin id);

    @Headers({"Content-Type: application/json"})
    @POST("userUpdate")
    Call<ResponseBody> actualizarUsuario(@Body Model_UserUpdate user);

    @Headers("Content-Type: application/json")
    @POST("addroom")
    Call<ResponseBody> addHabitacion(@Body AddRoom room);

    @Headers("Content-Type: application/json")
    @POST("addroom")
    Call<ResponseBody> createHabitacion(@Body AddRoom room);

    @Headers("Content-Type: application/json")
    @POST("contact")
    Call<ResponseBody> contacto(@Body Model_Contact contacto);

    @Headers("Content-Type: application/json")
    @GET("profiles/{user_id}")
    Call<Model_Perfiles> listadoPerfiles(@Path("user_id") int user_id);

    @Headers("Content-Type: application/json")
    @GET("profiles/{genero}/{user_id}")
    Call<Model_Perfiles> listadoPerfilesFiltro(@Path("genero") String genero, @Path("user_id") int user_id);

    @Headers("Content-Type: application/json")
    @POST("editRoom")
    Call<ResponseBody> updateHabitacion(@Body UpdateRoom room);

    @Headers("Content-Type: application/json")
    @POST("like")
    Call<Model_Like_Response> perfilLike(@Body Model_Like room);

    @Headers("Content-Type: application/json")
    @GET("getRequestChat/{user_id}")
    Call<Model_Chat_Response> getChats(@Path("user_id") int user_id);

    @Headers("Content-Type: application/json")
    @POST("acceptRequest")
    Call<ResponseBody> aceptarSolicitud(@Body Model_SolicitudAceptar aceptar);

    @Headers("Content-Type: application/json")
    @GET("getchat/{chat_id}")
    Call<Model_Chat_Conversacion> getMensajesChat(@Path("chat_id") int chat_id);

    @Headers("Content-Type: application/json")
    @GET("getchat/{chat_id}")
    Call<Model_Chat_Conversacion> getMensajesNext(@Path("chat_id") int chat_id, @QueryMap Map<String, Integer> params);

    @Headers("Content-Type: application/json")
    @POST("sendMessage")
    Call<Model_Chat_Mensaje_Response> enviarMensaje(@Body Model_Chat_Mensaje mensaje);

    @Headers("Content-Type: application/json")
    @POST("deleteChat")
    Call<ResponseBody> borrarChat(@Body Model_EliminarChat id);

}
