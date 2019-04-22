package c4_myaop2.service.impl;


import c4_myaop2.annotation.MyAnnoT;
import c4_myaop2.dao.UserDao;
import c4_myaop2.service.UserService;
import c4_myaop2.transaction.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
	注意事项： 在使用spring事务的时候，service 不要使用 try-catch， 要将异常抛出给
				外层aop异常通知（@AfterThrowing），然后在 异常通知 中进行回滚
*/
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TransactionUtils transactionUtils;

	@Override
	@MyAnnoT
	public void add() {
		userDao.add("wangmazi", 27);
		int i = 1 / 0;
		System.out.println("我是add方法");
		userDao.add("zhangsan", 16);
	}

}
