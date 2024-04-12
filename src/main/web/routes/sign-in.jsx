function SignIn() {
  return (
    <div id="container">
      <div id="sign">
        <h1>Přihlášení</h1>
        <form id="sign-form" action="/*TODO*/">
          <label htmlFor="#username">Uživatelské jméno</label>
          <input id="username" type="text" />
          <label htmlFor="#password">Heslo</label>
          <input id="password" type="password" />
          <input id="submit" value="Přihlásit" type="submit" />
        </form>
      </div>
    </div>
  );
}

export default SignIn