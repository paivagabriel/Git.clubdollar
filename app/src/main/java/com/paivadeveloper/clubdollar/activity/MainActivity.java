package com.paivadeveloper.clubdollar.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.paivadeveloper.clubdollar.R;
import com.paivadeveloper.clubdollar.util.BaseActivity;
import com.paivadeveloper.clubdollar.util.Font;
import com.paivadeveloper.clubdollar.util.Utils;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_GRID = 700;
    private Toolbar toolbar;
    private TextView tvToolbar;
    private GridLayout gridLayout;
    private LinearLayout llSocial;

    private CardView cvCortes;
    private CardView cvServicos;
    private CardView cvProdutos;
    private CardView cvCuriosidades;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        toolbar = findViewById(R.id.toolbar_teste);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = findViewById(R.id.drawer_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        cvCortes = findViewById(R.id.cv_cortes);
        cvCortes.setOnClickListener(click("SUGESTÕES DE CORTES"));

        cvServicos = findViewById(R.id.cv_servicos);
        cvServicos.setOnClickListener(click("SERVIÇOS"));

        cvProdutos = findViewById(R.id.cv_produtos);
        cvProdutos.setOnClickListener(click("PRODUTOS"));

        cvCuriosidades = findViewById(R.id.cv_curiosidades);
        cvCuriosidades.setOnClickListener(click("CURIOSIDADES"));

        findViewById(R.id.click_facebook).setOnClickListener(redeSocial("Facebook"));
        findViewById(R.id.click_twitter).setOnClickListener(redeSocial("Twitter"));
        findViewById(R.id.click_instagram).setOnClickListener(redeSocial("Instagram"));

        gridLayout = findViewById(R.id.grid_layout_teste);
        llSocial = findViewById(R.id.ll_social);

        tvToolbar = findViewById(R.id.tv_toolbar);
        Font.setKaushanScript(tvToolbar);

        View navHeader = navigationView.getHeaderView(0);

        if (savedInstanceState == null) {
            startIntroAnimation();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_empresa:
                toast("Informações sobre a Empresa.");
                break;
            case R.id.nav_equipe:
                toast("Apresenta a equipe.");
                break;
            case R.id.nav_grade_funcionamento:
                toast("Mostra a grade de funcionamento.");
                break;
            case R.id.nav_admin:
                Intent intent = new Intent(MainActivity.this, AdmActivity.class);
                startActivity(intent);
            case R.id.nav_info_app:

        }

        DrawerLayout drawer = findViewById(R.id.drawer_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

    private View.OnClickListener click(final String titulo) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snack("Você clicou em '" + titulo + "'.");
            }
        };
    }

    private View.OnClickListener redeSocial(final String redeSocial) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snack("Acessa a rede social: " + redeSocial);
            }
        };
    }

    private void startIntroAnimation() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int windowSize = size.x;
        int height = size.y;
        float heightSize = height / 5;
        gridLayout.setTranslationX(windowSize);
        llSocial.setTranslationY(heightSize);

        int actionbarSize = Utils.dpToPx(56);
        toolbar.setTranslationY(-actionbarSize);
        tvToolbar.setTranslationY(-actionbarSize);

        toolbar.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
        tvToolbar.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(400)
        .setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startContentAnimation();
            }
        })
        .start();
    }

    private void startContentAnimation() {
        gridLayout.animate()
                .translationX(0)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setStartDelay(300)
                .setDuration(ANIM_DURATION_GRID)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        startSocialNetworkAnimation();
                    }
                })
        .start();
    }

    private void startSocialNetworkAnimation() {
        llSocial.animate()
                .translationY(0)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setDuration(ANIM_DURATION_TOOLBAR)
                .start();
    }
}
