package com.example.registrecommobile;

public class User {

        int _id;
        String _username;
        String _password;
        public User(){ }
        public User(int id, String username, String _password){
            this._id = id;
            this._username = username;
            this._password = _password;
        }
        public User(String username, String _password){
            this._username = username;
            this._password = _password;
        }
        public int getID(){
            return this._id;
        }
        public void setID(int id){
            this._id = id;
        }
        public String getUsername(){
            return this._username;
        }
        public void setUsername(String username){
            this._username = username;
        }

        public String getPassword(){
            return this._password;
        }
        public void setPassword(String password){
            this._password = password;
        }

}
