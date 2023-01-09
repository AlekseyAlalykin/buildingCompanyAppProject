package com.example.services;

import com.example.activities.R;
import com.example.model.Service;

import java.util.LinkedList;
import java.util.List;

public class ServiceManager {
    static private ServiceManager serviceManager;

    private List<Service> services;

    private ServiceManager(){
        services = new LinkedList<>();

        //Тестовые примеры
        services.add(new Service("Поклейка обоев",
                "Наши мастера приедут на ваш адрес и поклеют ваи обои",
                "₽249 за 1м^2", R.drawable.wallpaper_rolls));

        services.add(new Service("Укладка ламината",
                "Наши мастера приедут на ваш адрес уложат вам ламинат",
                "₽369 за 1м^2", R.drawable.laminate));

        services.add(new Service("Покраска стен",
                "Наши мастера приедут на ваш адрес и покрасят вам стены",
                "₽300 за 1м^2", R.drawable.paint_cans));

        services.add(new Service("Укладка плитки",
                "Наши мастера приедут на ваш адрес и уложат вам плитку",
                "₽400 за 1м^2", R.drawable.tiles));

        services.add(new Service("Установка двери",
                "Наши мастера приедут на ваш адрес и установят вам дверь",
                "₽5000", R.drawable.door));
    }

    public static ServiceManager getInstance(){
        if (serviceManager == null)
            serviceManager = new ServiceManager();

        return serviceManager;
    }

    public List<Service> getServices() {
        return services;
    }
}
