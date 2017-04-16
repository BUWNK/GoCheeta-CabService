<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.avn.ccl.model.section.Section" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- User info -->

<div class="login-info">
    <span> <!-- User image size is adjusted inside CSS, it should stay as it --> 
        <a id="show-shortcut" data-action="toggleShortcut">
            <i class="fa fa-2x fa-fw fa-user"></i> <span class="menu-item-parent"><%= session.getAttribute("username") %></span>
        </a> 
    </span>
    <%--<sec:authentication property="principal.username"/>--%>
</div>
<!-- end user info -->

<!-- NAVIGATION : This navigation is also responsive

To make this navigation dynamic please make sure to link the node
(the reference to the nav > ul) after page load. Or the navigation
will not initialize.
-->

<nav>
    <!-- NOTE: Notice the gaps after each icon usage <i></i>..
    Please note that these links work a bit different than
    traditional href="" links. See documentation for details.
    -->

    <ul>
        <%
            List<Section> sections = (List<Section>) session.getAttribute("pagehierarchy");
            for (int i = 0; i < sections.size(); i++) {
        %>
        <li>
            <%
                if (sections.get(i).getOnlyparent().equalsIgnoreCase("1")) {
            %>
            <a href="${pageContext.servletContext.contextPath}<%= sections.get(i).getUrl()%>" title="<%= sections.get(i).getSectionDes()%>">
                <i class="<%= sections.get(i).getIcon()%>"></i>
                <span class="menu-item-parent"><%= sections.get(i).getSectionDes()%></span>
            </a>
            <%
            } else {
            %>
            <a href="javascript:void(0);">
                <i class="<%= sections.get(i).getIcon()%>"></i>
                <span class="menu-item-parent"><%= sections.get(i).getSectionDes()%></span>
            </a>
            <ul>
                <%
                    for (int j = 0; sections.get(i).getChildsections() != null && j < sections.get(i).getChildsections().size(); j++) {
                %>
                <li>
                    <a href="javascript:void(0);">
                        <i class="<%= sections.get(i).getChildsections().get(j).getIcon()%>"></i>
                        <span class="menu-item-parent"><%= sections.get(i).getChildsections().get(j).getSectionDes()%></span>
                    </a>
                    <ul>
                        <%
                            for (int k = 0; sections.get(i).getChildsections().get(j).getSubsections() != null && k < sections.get(i).getChildsections().get(j).getSubsections().size(); k++) {
                        %>
                        <li class="sub_navigation">
                            <a href="${pageContext.servletContext.contextPath}<%= sections.get(i).getChildsections().get(j).getSubsections().get(k).getUrl()%>" 
                               title="<%= sections.get(i).getChildsections().get(j).getSubsections().get(k).getSubsectionDes()%>"
                               class="<%= sections.get(i).getChildsections().get(j).getSubsections().get(k).getClickable().equalsIgnoreCase("1") ? "" : "left-navigation-disabled-link"%>">
                                <c:out value="<%= sections.get(i).getChildsections().get(j).getSubsections().get(k).getSubsectionDes()%>"/>
                            </a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                </li>
                <%
                    }
                    for (int k = 0; sections.get(i).getSubsections() != null && k < sections.get(i).getSubsections().size(); k++) {
                %>
                <li>
                    <a href="${pageContext.servletContext.contextPath}<%= sections.get(i).getSubsections().get(k).getUrl()%>" 
                       title="<%= sections.get(i).getSubsections().get(k).getSubsectionDes()%>"
                       class="<%= sections.get(i).getSubsections().get(k).getClickable().equalsIgnoreCase("1") ? "" : "left-navigation-disabled-link"%>">
                        <c:out value="<%= sections.get(i).getSubsections().get(k).getSubsectionDes()%>"/>
                    </a>
                </li>
                <%
                    }
                %>
            </ul>
            <%
                }
            %>
        </li>
        <%
            }
        %>
    </ul>

</nav>

<span class="minifyme" data-action="minifyMenu"> 
    <i class="fa fa-arrow-circle-left hit"></i> 
</span>


