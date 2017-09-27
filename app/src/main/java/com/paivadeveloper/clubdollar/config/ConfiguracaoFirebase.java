package com.paivadeveloper.clubdollar.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Gabriel on 15/09/2017.
 */

public final class ConfiguracaoFirebase {

    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;
    private static StorageReference firebaseStorage;

    public static DatabaseReference getFirebase(){

        if (referenciaFirebase == null) {
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }

    public static FirebaseAuth getFirebaseAutenticacao(){
        if (autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return  autenticacao;
    }

    /**
     * Criado em 21/09/2017 por thalles
     *
     *  o @return deste metodo é a referencia do storage, que guarda as fotos
     *  do adm.
     */
    public static StorageReference getFirebaseStorage(){

        if(firebaseStorage == null){
            firebaseStorage = FirebaseStorage.getInstance().getReference();
        }

        return firebaseStorage;
    }

}
