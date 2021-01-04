package com.vuha.repository;

import com.vuha.domain.User;
import com.vuha.util.JSONUtil;
import com.vuha.util.STRINGUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
public class UserRepository {

    String dataDirectory = "data/users.json";
    Map<Integer, User> userData = new HashMap<>();

    @PostConstruct
    public void init() throws IOException, ParseException {
        URL resource = getClass().getClassLoader().getResource(dataDirectory);
        if (resource != null) {
            JSONArray jsonArray = (JSONArray) JSONUtil.parseReaderFromPath(resource.getPath());
            List<User> userList = JSONUtil.jsonArrayToObjList(jsonArray, User.class);
            for (User item : userList) {
                userData.put(item.getId(), item);
            }
        }
    }

    public List<User> findAll() {
        return new ArrayList<>(userData.values());
    }

    public Optional<User> findById(int id) {
        User u = userData.get(id);
        if (u == null) {
            return Optional.empty();
        }

        return Optional.of(u);
    }

    public List<User> search(String field, String keyword) {
        List<User> users = new ArrayList<>();

        if (StringUtils.isEmpty(field)) {
            users = StringUtils.isEmpty(keyword) ? findAll() : new ArrayList<>();
            return users;
        }

        try {
            switch (field) {
                case "_id":
                    if (userData.containsKey(Integer.parseInt(keyword))) {
                        users.add(userData.get(Integer.parseInt(keyword)));
                    }
                    break;
                case "url":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (StringUtils.equals(user.getUrl(), keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "external_id":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (StringUtils.equals(user.getExternalId(), keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "name":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (STRINGUtil.separateWord(user.getName()).contains(keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "alias":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (STRINGUtil.separateWord(user.getAlias()).contains(keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "created_at":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (StringUtils.equals(user.getCreatedAt(), keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "active":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (user.isActive() == Boolean.parseBoolean(keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "verified":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (user.isVerified() == Boolean.parseBoolean(keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "shared":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (user.isShared() == Boolean.parseBoolean(keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "locale":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (StringUtils.equals(user.getLocale(), keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "timezone":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (StringUtils.equals(user.getTimezone(), keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "last_login_at":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (StringUtils.equals(user.getLastLoginAt(), keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "email":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (StringUtils.equals(user.getEmail(), keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "phone":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (StringUtils.equals(user.getPhone(), keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "signature":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (StringUtils.equals(user.getSignature(), keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "organization_id":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (user.getOrganizationId() == Integer.parseInt(keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "tags":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (user.getTags().contains(keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "suspended":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (user.isSuspended() == Boolean.parseBoolean(keyword)) {
                            users.add(user);
                        }
                    }
                    break;
                case "role":
                    for (Map.Entry<Integer, User> entry : userData.entrySet()) {
                        User user = entry.getValue();
                        if (StringUtils.equals(user.getRole(), keyword)) {
                            users.add(user);
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
