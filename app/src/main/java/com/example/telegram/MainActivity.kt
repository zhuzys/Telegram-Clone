package com.example.telegram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import com.example.telegram.activities.RegisterActivity

import com.example.telegram.databinding.ActivityMainBinding
import com.example.telegram.fragments.ChatsFragment
import com.example.telegram.fragments.SettingsFragment
import com.example.telegram.utils.AUTH
import com.example.telegram.utils.replaceActivity
import com.example.telegram.utils.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader

    private lateinit var mToolbar : Toolbar
  //  private lateinit var  mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFields()
        initFunc()

     ///   mToolbar = binding.mainToolbar
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        //    setSupportActionBar(binding.mainToolbar)
        //проверяем авторизован пользователь или нет
        if (AUTH.currentUser != null) {

            //если пользователь автаризован
            //устаналиваем чаты фрагменты
            replaceFragment(ChatsFragment(),false)
           /* supportFragmentManager.beginTransaction()
                .replace(R.id.dataContainer, ChatsFragment()).commit()*/
            createHeader()
            createDrawer() //вдвижной меню

        } else {
            replaceActivity(RegisterActivity())
        }
    }

    private fun createDrawer() {
        mDrawer = DrawerBuilder()
                .withActivity(this)
                .withToolbar(binding.mainToolbar)
                .withActionBarDrawerToggle(true)
                .withSelectedItem(-1)
                .withAccountHeader(mHeader)
                .addDrawerItems (
                        PrimaryDrawerItem().
                        withIdentifier(100).
                        withIconTintingEnabled(true).
                        withName("Создать группу")
                                .withSelectable(false)
                                .withIcon(R.drawable.ic_menu_create_groups),
                        PrimaryDrawerItem().
                        withIdentifier(101).
                        withIconTintingEnabled(true).
                        withName("Создать секретный чат")
                                .withSelectable(false)
                                .withIcon(R.drawable.ic_menu_secret_chat),
                        PrimaryDrawerItem().
                        withIdentifier(102).
                        withIconTintingEnabled(true).
                        withName("Создать канал")
                                .withSelectable(false)
                                .withIcon(R.drawable.ic_menu_create_channel),
                        PrimaryDrawerItem().
                        withIdentifier(103).
                        withIconTintingEnabled(true).
                        withName("Контакты")
                                .withSelectable(false)
                                .withIcon(R.drawable.ic_menu_contacts),
                        PrimaryDrawerItem().
                        withIdentifier(104).
                        withIconTintingEnabled(true).
                        withName("Звонки")
                                .withSelectable(false)
                                .withIcon(R.drawable.ic_menu_phone),
                        PrimaryDrawerItem().
                        withIdentifier(105).
                        withIconTintingEnabled(true).
                        withName("Избранное")
                                .withSelectable(false)
                                .withIcon(R.drawable.ic_menu_favorites),
                        PrimaryDrawerItem().
                        withIdentifier(106).
                        withIconTintingEnabled(true).
                        withName("Настройки")
                                .withSelectable(false)
                                .withIcon(R.drawable.ic_menu_settings),


                        DividerDrawerItem(),

                        PrimaryDrawerItem().
                        withIdentifier(109).
                        withIconTintingEnabled(true).
                        withName("Пригласить друзей")
                                .withSelectable(false)
                                .withIcon(R.drawable.ic_menu_invate),
                        PrimaryDrawerItem().
                        withIdentifier(110).
                        withIconTintingEnabled(true).
                        withName("Вопросы по телеграм")
                                .withSelectable(false)
                                .withIcon(R.drawable.ic_menu_help)
                        //Повесим слушатель нажатие
                ).withOnDrawerItemClickListener(object: Drawer.OnDrawerItemClickListener {
                    override fun onItemClick(
                        view: View?,
                        position: Int,
                        drawerItem: IDrawerItem<*>
                    ): Boolean {
                        when(position) {
                            2->  supportFragmentManager.beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.data_container, ChatsFragment()).commit()
                            7->  supportFragmentManager.beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.data_container, SettingsFragment()).commit()

                        }
                        Toast.makeText(applicationContext, position.toString(), Toast.LENGTH_SHORT).show()
                        return false

                    }
                }).build()
    }

    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        ProfileDrawerItem().withName("Zhuzy Aman")
                                .withEmail("zh.amankeldi@aues.kz")
                ).build()
    }


   private fun initFields() {
       AUTH = FirebaseAuth.getInstance()
       /* Функция инициализирует переменные */

      // mAppDrawer = AppDrawer()
   }
}


