package com.example.demo.service

import com.example.demo.entity.Message
import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*

@Service
class MessageService(private val db: JdbcTemplate) {
    fun findMessages(): List<Message> = db.query("select * from messages") {
        rs, _ ->
        Message(rs.getString("id"), rs.getString("text"))
    }

    fun save(message: Message): Message {
        db.update(
            "insert into messages (id, text) values (?, ?)",
            message.id, message.text
        )

        return message
    }
}