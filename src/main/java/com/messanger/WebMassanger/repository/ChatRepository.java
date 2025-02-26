package com.messanger.WebMassanger.repository;


import com.messanger.WebMassanger.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Long> {
    List<Chat> findAllByFriendNameAndMyName(String friendName, String myName);
    List<Chat> findAllByFriendNameAndMyNameOrderByTimestamp(String friendName,String myName);


}
