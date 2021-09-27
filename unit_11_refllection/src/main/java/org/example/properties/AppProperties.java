package org.example.properties;

import org.example.annotation.PropertyKey;

import java.util.Date;

public class AppProperties {

    @PropertyKey(value = "connections.amount")
    public long amountConnections;

    @PropertyKey(value = "connections.limit")
    public int maxConnections;

    @PropertyKey(value = "connection.date")
    public Date dateConnection;

    @PropertyKey(value = "connection.name")
    public String nameConnection;

    @PropertyKey(value = "float.number")
    public float floatNumber;
}
