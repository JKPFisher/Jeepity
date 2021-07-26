package com.example.jeepitybasic

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_offline_routes.*
import kotlinx.android.synthetic.main.ts_minesview_activity.*

class OfflineRoutes  : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_offline_routes)



        orMinesView.setOnClickListener{
            setContentView(R.layout.ts_minesview_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
                                            }
        orbotanicalgarden.setOnClickListener {
            setContentView(R.layout.ts_botanical_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
                                            }
        orLionsHead.setOnClickListener {
            setContentView(R.layout.ts_lionshead_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
                                      }
        orbellchurch.setOnClickListener{
            setContentView(R.layout.ts_bellchurch_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
                                      }
        orbellchurch.setOnClickListener       {
            setContentView(R.layout.ts_bellchurch_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
        orbellhouse.setOnClickListener       {
            setContentView(R.layout.ts_bellhouse_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
        orburnham.setOnClickListener       {
            setContentView(R.layout.ts_burnham_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
        ormuseum.setOnClickListener       {
            setContentView(R.layout.ts_museum_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
        ornegativism.setOnClickListener       {
            setContentView(R.layout.ts_negativism_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
        ornightmarket.setOnClickListener       {
            setContentView(R.layout.ts_nightmarket_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
        ortreetop.setOnClickListener       {
            setContentView(R.layout.ts_treetopadventure_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
        orwrightpark.setOnClickListener       {
            setContentView(R.layout.ts_wrightpark_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
        oryellowtrail.setOnClickListener       {
            setContentView(R.layout.ts_yellowtrail_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
        orcolors.setOnClickListener       {
            setContentView(R.layout.ts_colors_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
        orbencab.setOnClickListener       {
            setContentView(R.layout.ts_bencab_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
        orstrawberry.setOnClickListener       {
            setContentView(R.layout.ts_strawberryfarm_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
        ortamawan.setOnClickListener       {
            setContentView(R.layout.ts_tamawan_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
        ormansion.setOnClickListener       {
            setContentView(R.layout.ts_themansion_activity)

            this.supportActionBar?.hide()
            button.setOnClickListener{ val intent = Intent(
                this,
                OfflineRoutes::class.java)
                finish()
                startActivity(intent)}
        }
    }

}



