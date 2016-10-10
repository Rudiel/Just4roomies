package com.gloobe.just4roomies.Actividades;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.gloobe.just4roomies.Adaptadores.ViewPagerAdapter;
import com.gloobe.just4roomies.Fragments.Fragment_BuscarRoomie;
import com.gloobe.just4roomies.Fragments.Fragment_Chat;
import com.gloobe.just4roomies.R;


/**
 * Created by rudielavilaperaza on 17/08/16.
 */
public class Activity_Principal extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private ImageView ivImagenPerfilMenu;
    private Profile profile;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_principal);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.vpPrincipal);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);

        ivImagenPerfilMenu = (ImageView) headerView.findViewById(R.id.ivImagenPerfilMenu);

        setSupportActionBar(toolbar);

        setupViewPager(viewPager);

        profile = Profile.getCurrentProfile();

        //String fName = getIntent().getExtras().getInt("fname");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_cerrarsesion:
                        LoginManager.getInstance().logOut();
                        drawerLayout.closeDrawers();
                        cerrarSesion();
                        return true;
                    case R.id.menu_editarperfil:
                        return true;
                }
                return false;
            }
        });

        //tabLayout.setupWithViewPager(viewPager);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.app_name, R.string.app_name
        );

        drawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

        //Si se quiere habilitar el scroll del tablayout
        viewPager.beginFakeDrag();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.tab_buscarroomie_active));
                        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.tab_chat_inactive));
                        break;
                    case 1:
                        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.tab_buscarroomie_inactive));
                        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.tab_chat_active));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (profile != null) {

            Glide.with(Activity_Principal.this).load(profile.getProfilePictureUri(150, 150)).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivImagenPerfilMenu) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getBaseContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivImagenPerfilMenu.setImageDrawable(circularBitmapDrawable);
                }
            });

        }


    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.tab_buscarroomie_active));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.tab_chat_inactive));
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_BuscarRoomie(), "ONE");
        adapter.addFragment(new Fragment_Chat(), "TWO");
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    private void cerrarSesion() {
        Intent intent = new Intent(Activity_Principal.this, Activity_Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
