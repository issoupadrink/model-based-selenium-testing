package com.modelbasedseleniumtesting.model;

import org.graphwalker.core.model.Edge;
import org.graphwalker.core.model.Model;
import org.graphwalker.core.model.Vertex;


public class HackerNewsModel {

    Model model;

    public HackerNewsModel() {
        this.model = generateModel();
    }

    public Model generateModel() {
        Model model = new Model();

        Vertex v_Start = new Vertex().setName("v_Start");
        Vertex v_Homepage = new Vertex().setName("v_Homepage");
        Vertex v_FailedLogin = new Vertex().setName("v_FailedLogin");
        Vertex v_Login = new Vertex().setName("v_Login");
        Vertex v_HomePageLoggedIn = new Vertex().setName("v_HomePageLoggedIn");

        Edge e_StartBrowser = new Edge()
                .setName("e_StartBrowser")
                .setSourceVertex(v_Start)
                .setTargetVertex(v_Homepage);
        Edge e_GoToLogin = new Edge()
                .setName("e_GoToLogin")
                .setSourceVertex(v_Homepage)
                .setTargetVertex(v_Login);
        Edge e_SuccessfulLogin = new Edge()
                .setName("e_SuccessfulLogin")
                .setSourceVertex(v_Login)
                .setTargetVertex(v_HomePageLoggedIn);
        Edge e_FailedLogin = new Edge()
                .setName("e_FailedLogin")
                .setSourceVertex(v_Login)
                .setTargetVertex(v_FailedLogin);
        Edge e_SuccessfulLoginAfterFailure = new Edge()
                .setName("e_SuccessfulLoginAfterFailure")
                .setSourceVertex(v_FailedLogin)
                .setTargetVertex(v_HomePageLoggedIn);
        Edge e_Logout = new Edge()
                .setName("e_Logout")
                .setSourceVertex(v_HomePageLoggedIn)
                .setTargetVertex(v_Homepage);

        model.addVertex(v_Start);
        model.addVertex(v_Homepage);
        model.addVertex(v_FailedLogin);
        model.addVertex(v_Login);
        model.addVertex(v_HomePageLoggedIn);

        model.addEdge(e_StartBrowser);
        model.addEdge(e_GoToLogin);
        model.addEdge(e_SuccessfulLogin);
        model.addEdge(e_FailedLogin);
        model.addEdge(e_SuccessfulLoginAfterFailure);
        model.addEdge(e_Logout);

        return model;
    }
}
