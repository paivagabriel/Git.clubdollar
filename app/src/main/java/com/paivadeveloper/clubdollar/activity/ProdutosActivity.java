package com.paivadeveloper.clubdollar.activity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.paivadeveloper.clubdollar.R;
import com.paivadeveloper.clubdollar.config.ConfiguracaoFirebase;
import com.paivadeveloper.clubdollar.util.CustomProgressDialog;
import com.paivadeveloper.clubdollar.util.ImageUploadInfo;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ProdutosActivity extends AppCompatActivity {

    private String storagePath, dataBasePath;
    private Button chooseButton, uploadButton;
    private EditText imageName;
    private ImageView selectedImage;
    private Uri FilePathUri;
    private CustomProgressDialog progressDialog;
    private int image_Request_Code;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        FirebaseApp.initializeApp(getApplicationContext());

        storagePath = "Club_Dollar/";

        //alteração no caminho da arvore
        dataBasePath = "Salao/produtos/nome_produto/fotos_storage_id";

        storageReference = FirebaseStorage.getInstance().getReference(storagePath);
        databaseReference = ConfiguracaoFirebase.getFirebase().child(dataBasePath);
        uploadButton = findViewById(R.id.produto_btn_upload);
        imageName = findViewById(R.id.produto_edit_id);
        selectedImage = findViewById(R.id.produto_img);

        selectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // nova intent
                Intent i = new Intent();
                //define o tipo da intent
                // na verdade não sei direito oque faz XD
                i.setType("image/");
                //define a ação da intent
                i.setAction(Intent.ACTION_GET_CONTENT);
                // aqui faz com que a intent i, abara uma busca de imagens
                startActivityForResult(Intent.createChooser(i, "Selecione uma imagem"), image_Request_Code);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling method to upload selected image on Firebase storage.
                // chama methodo para fazer upload
                UploadImageFileToFirebaseStorage();
            }
        });

    }
// Metodo para pegar extensão da imagem selecionada do FilePathURI
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {

                // transforma a imagem em bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // poe o bitmap na ImageView.
                selectedImage.setImageBitmap(bitmap);

                // depois de fazer atribuição troca o nome do botao CHOOSE.
                

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    // Metodo que faz o upload no storage
    public void UploadImageFileToFirebaseStorage() {

        // checa se o caminho do arquivo não é vazio
        if (FilePathUri != null) {

            progressDialog = new CustomProgressDialog(this);
            progressDialog.create();

            // inicia o processDialog.
            progressDialog.setCancelable(false);
            // Mostra progressDialog.
            progressDialog.show();

            //muda o nome do arquivo pra data e HOra do sistema ... que foi enviado.

            long date = System.currentTimeMillis();
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("d_M_yyyy_HH_mm");
            String hoje = sdf.format(date);

            // Cria outra storageReference. --nao entendi porque, mas funciona
            StorageReference storageReference2nd = storageReference.child( hoje + "." + GetFileExtension(FilePathUri));

            // adiciona addOnSuccessListener ao segundo StorageReference.
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // pega o nome da imagem e insere no EditText
                            String TempImageName = imageName.getText().toString().trim();
                             // esconde o processDialog
                            progressDialog.dismiss();

                            // Mostra toast de sucesso
                            Toast.makeText(getApplicationContext(),
                                    "Imagem Carregada com Sucesso! ", Toast.LENGTH_LONG).show();

                            @SuppressWarnings("Visivel Para teste")
                            ImageUploadInfo imageUploadInfo = new ImageUploadInfo(
                                    TempImageName, taskSnapshot.getDownloadUrl().toString()
                            );

                            // pega Id da imagem.
                            String ImageUploadId = databaseReference.push().getKey();

                            // Adiciona ids como elemento filho na base de dados do firebase.
                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                        }
                    })
                    // se algo der errado...
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // esconde o progressDialog.
                            progressDialog.dismiss();

                            // Mostra mesnsagem de erro -> exception
                            Toast.makeText(getApplicationContext(),
                                    "Algo deu errado, tente novamente.", Toast.LENGTH_LONG).show();
                        }
                    })
                    // ao mudar progresso do upload.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            // define o titulo do process dialog.
                            progressDialog.setTitle("Trabalhando nisso...");
                        }
                    });
        }
        else {

            Toast.makeText(getApplicationContext(), "Selecione Uma Imagem!!!", Toast.LENGTH_LONG).show();
        }
    }

}


