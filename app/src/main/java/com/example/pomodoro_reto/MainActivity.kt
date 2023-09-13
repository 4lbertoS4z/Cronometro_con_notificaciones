package com.example.pomodoro_reto

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    val chanelId = "chat"
    val chanelName = "chat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonPlay = findViewById<FloatingActionButton>(R.id.buttonPlay)
        val buttonPause = findViewById<FloatingActionButton>(R.id.buttonPause)
        val buttonStop = findViewById<FloatingActionButton>(R.id.buttonStop)
        val time = findViewById<Chronometer>(R.id.time)
        var pauseOffSet: Long = 0



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importancia = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(chanelId, chanelName, importancia)

            //manager de notificaciones
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            //Define sound URI
            //Define sound URI


            time.setFormat("%s")
            time.setBase(SystemClock.elapsedRealtime())

            buttonPlay.setOnClickListener { v ->
                //configuracion notificacion


                val notificacion = NotificationCompat.Builder(this, chanelId).also { noti ->
                    // noti.setUsesChronometer(true) // muestra el tiempo en la notificacion
                    noti.setContentTitle("Cronometro funcionando")
                    noti.setContentText("")
                    noti.setSmallIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                }.build()
                val notificationManager = NotificationManagerCompat.from(applicationContext)
                notificationManager.notify(1, notificacion)
                Toast.makeText(this, "Boton play pulsado", Toast.LENGTH_SHORT).show()
                time.setBase(SystemClock.elapsedRealtime() - pauseOffSet)
                time.start()
            }


        }
        buttonPause.setOnClickListener { v ->

            val notificacion = NotificationCompat.Builder(this, chanelId).also { noti ->
                noti.setContentTitle("Cronometro Pausado")
                noti.setSmallIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
            }.build()
            val notificationManager = NotificationManagerCompat.from(applicationContext)
            notificationManager.notify(1, notificacion)
            Toast.makeText(this, "Boton pausa pulsado", Toast.LENGTH_SHORT).show()
            pauseOffSet = SystemClock.elapsedRealtime() - time.getBase()
            time.stop()
        }
        buttonStop.setOnClickListener { v ->

            val notificacion = NotificationCompat.Builder(this, chanelId).also { noti ->
                noti.setContentTitle("Cronometro Detenido")
                noti.setSmallIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
            }.build()
            val notificationManager = NotificationManagerCompat.from(applicationContext)
            notificationManager.notify(1, notificacion)
            Toast.makeText(this, "Boton Stop pulsado", Toast.LENGTH_SHORT).show()
            time.stop()
            time.setBase(SystemClock.elapsedRealtime())
            pauseOffSet = 0

        }

    }

}