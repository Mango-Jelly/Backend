package com.mangojelly.backend.domain.socket;

public record Message(String type, String sender, Object data) { }
