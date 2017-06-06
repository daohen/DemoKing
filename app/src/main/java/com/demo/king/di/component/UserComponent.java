package com.demo.king.di.component;

import com.demo.king.User1Activity;
import com.demo.king.User2Activity;
import com.demo.king.di.UserScope;
import com.demo.king.di.module.UserModule;

import dagger.Subcomponent;

/**
 * CREATE BY ALUN
 * EMAIL: alunfeixue2011@gmail.com
 * DATA : 2017/06/06 09:09
 *
 * module里面可provide的对象有作用域标记时，这里同样也要声明
 * 如：UserModule里面有@UserScope，所以这里也同样声明
 */
@UserScope
@Subcomponent(modules = UserModule.class)
public interface UserComponent {

    /**
     * 下面两个方法，声明哪些页面可注入UserModule提供的对象
     * @param activity
     */

    void inject(User1Activity activity);
    void inject(User2Activity activity);

}
