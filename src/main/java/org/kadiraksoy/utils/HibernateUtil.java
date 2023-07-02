package org.kadiraksoy.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.kadiraksoy.model.*;


//Hibernate ile bir veritabanı bağlantısını yönetmek için bir SessionFactory oluşturur.
// SessionFactory, Hibernate tarafından sağlanan bir yapıdır
// ve uygulama ile veritabanı arasında iletişim kurmayı sağlar.
public class HibernateUtil {



    //sessionFactory null ise, yeni bir SessionFactory oluşturmak için gerekli adımlar gerçekleştirilir.
    private static SessionFactory sessionFactory=null;



    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            try{
                //Yeni bir Configuration nesnesi oluşturulur.
                // Bu, Hibernate yapılandırmasını ve ilişkilendirilmiş sınıfları belirlemek için kullanılır.
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(FilmCategory.class);//Hibernate tarafından yönetilen sınıfların tanımlandığı eklenir.
                configuration.addAnnotatedClass(Award.class);
                configuration.addAnnotatedClass(Film.class);
                configuration.addAnnotatedClass(Actor.class);
                configuration.addAnnotatedClass(Director.class);

                //Hibernate yapılandırma dosyası belirtilir.
                // Bu, veritabanı bağlantısı ve diğer Hibernate ayarlarını içerir.
                sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();//yapılandırmaya dayalı olarak bir SessionFactory nesnesi oluşturur ve bunu sessionFactory değişkenine atar.
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
