document.getElementById("registerForm").addEventListener("submit", async function(event) {
    event.preventDefault();
    const data = {
        UserFirstName: document.getElementById("UserFirstName").value,
        UserLastName: document.getElementById("UserLastName").value,
        UserName: document.getElementById("UserName").value,
        UserPassword: document.getElementById("UserPassword").value,
        UserRole: document.getElementById("UserRole").value
    };

    const response = await fetch("/Registration/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    const result = await response.json();

    if (response.ok) {
        document.getElementById("registerResponse").innerText = JSON.stringify(result, null, 2);
        document.getElementById("registerModal").style.display = "block";
    } else {
        alert("Registration failed!");
    }
});

document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault();
    const data = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value
    };

    const response = await fetch("/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        const result = await response.json();
        document.getElementById("jwtToken").innerText = result.token;
        document.getElementById("tokenModal").style.display = "block";
    } else {
        alert("Login failed!");
    }
});

function closeModal() {
    document.getElementById("tokenModal").style.display = "none";
    showLogin();
}

function closeRegisterModal() {
    document.getElementById("registerModal").style.display = "none";
    showLogin();
}

function showRegister() {
    document.getElementById("loginSection").style.display = "none";
    document.getElementById("registerSection").style.display = "block";
}

function showLogin() {
    document.getElementById("registerSection").style.display = "none";
    document.getElementById("loginSection").style.display = "block";
}
