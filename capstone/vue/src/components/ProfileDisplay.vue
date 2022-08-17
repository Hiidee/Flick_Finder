<template>
  <div>
      <p>Username: {{this.$store.state.user.username}}</p>
      <p>First Name: {{this.$store.state.userProfile.firstName}}</p>
      <p>Last Name: {{this.$store.state.userProfile.lastName}}</p>
      <p>Email Address: {{this.$store.state.userProfile.emailAddress}}</p>
  </div>
</template>

<script>
export default {
    name: 'profile-display'
}
</script>

<style>

</style>