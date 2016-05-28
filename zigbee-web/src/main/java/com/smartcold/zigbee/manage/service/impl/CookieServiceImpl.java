package com.smartcold.zigbee.manage.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcold.zigbee.manage.dao.CookieMapper;
import com.smartcold.zigbee.manage.entity.CookieEntity;
import com.smartcold.zigbee.manage.service.CookieService;
import com.smartcold.zigbee.manage.util.EncodeUtil;

@Service
public class CookieServiceImpl implements CookieService {

	@Autowired
	private CookieMapper cookieDao;

	@Override
	public String insertCookie(String username) {
		Date date = new Date();
		CookieEntity cookieEntity = new CookieEntity();

		String encode = EncodeUtil.encode("sha1", String.format("%s%s", username, date.getTime()));
		cookieEntity.setUsername(username);
		cookieEntity.setCookie(encode);
		cookieEntity.setExpireTime(EXPIERD_TIME);
		cookieDao.insertCookie(cookieEntity);

		return encode;
	}

	@Override
	public CookieEntity findEffectiveCookie(String cookie) {
		return cookieDao.findEffectiveCookie(cookie);
	}

	@Override
	public void deleteCookie(String cookie) {
		cookieDao.deleteCookie(cookie);
	}

}
