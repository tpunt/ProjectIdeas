/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.ProjectIdeas = window.ProjectIdeas || {};
window.ProjectIdeas.Router = (function () {
    'use strict';

    var registerELs = function () {
        var element = document.getElementById("results");

        element.addEventListener('click', function (e) {
            var id = null, clicked = e.target;

            if (clicked.dataset.id) {
                id = clicked.dataset.id;
            } else {
                var parent = clicked.parentNode;

                switch (parent.nodeName) {
                    case "TD":
                        var dataset = parent.childNodes[1].dataset;

                        if (dataset && dataset.id) {
                            id = dataset.id;
                        } else {
                            var dataset = parent.parentNode.childNodes[1].childNodes[0].dataset;

                            if (dataset && dataset.id) {
                                id = dataset.id;
                            }
                        }

                        break;
                    case "TR":
                        var dataset = parent.childNodes[1].childNodes[0].dataset;

                        if (dataset && dataset.id) {
                            id = dataset.id;
                        }
                }
            }

            if (id !== null) {
                history.pushState(null, null, "project.xhtml?id=" + id);
            }
        });
    },
        toggleELs = function() {
            var content = document.getElementById('content');
            
            content.addEventListener('change', function(e) {
                //
            });
        };
    
    return {
        registerELs: registerELs
    };
}());

window.addEventListener('load', window.ProjectIdeas.Router.registerELs);
