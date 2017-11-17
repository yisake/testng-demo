package com.nico.testcases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups="provider")
public class ProviderDemo {

    // 返回Iterator<Object[]>
    @DataProvider(name = "iterator")
    public Iterator<Object[]> dp1() {
        List<Object> item = new ArrayList<Object>();

        // 添加第一个用户
        UserInfo user1 = new UserInfo();
        user1.setUserName("User1");
        user1.setPassword("Password1");
        item.add(user1);

        // 添加第二个用户
        UserInfo user2 = new UserInfo();
        user2.setUserName("User2");
        user2.setPassword("Password2");
        item.add(user2);

        List<Object[]> users = new ArrayList<Object[]>();

        for (Object u : item) {
            //做一个形式转换
            users.add(new Object[] { u });
        }
        return users.iterator();
    }
    
    @Test(dataProvider = "iterator")
    public void testProvider(UserInfo user) {
        System.out.println("User Name is: "+ user.getUserName() +"\n"+"User password is" + user.getPassword());
    }    
	
}
