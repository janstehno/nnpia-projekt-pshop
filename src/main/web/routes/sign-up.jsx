function SignUp() {
  return (
    <div id="container">
      <div id="sign">
        <h1>Registrace</h1>
        <form id="sign-form" action="/*TODO*/">
          <label htmlFor="#firstname">Jméno</label>
          <input id="firstname" type="text" />
          <label htmlFor="#lastname">Příjmení</label>
          <input id="lastname" type="text" />
          <label htmlFor="#username">Uřivatelské jméno</label>
          <input id="username" type="text" />
          <label htmlFor="#password">Heslo</label>
          <input id="password" type="password" />
          <input id="submit" value="Zaregistrovat" type="submit" />
        </form>
      </div>
    </div>
  );
}

export default SignUp